package sendData;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

public class AvroFootball {


    public static void main(String[] args) throws IOException, FileNotFoundException {
        URL url = new URL("https://api.football-data.org/v4/areas");
        String schemaString = parseAvro();
        Schema.Parser parser = new Schema.Parser();
        Schema schema = parser.parse(schemaString);
        GenericRecord avroRecord = new GenericData.Record(schema);
        Properties properties = getProperties();
        FootballData sendFootbalData = storeData(url);
        KafkaProducer<String, Object> producer = new KafkaProducer<>(properties);
        String topic = "firstProjectTopic";
        avroRecord.put("FootbalData", sendFootbalData);
        ProducerRecord<String, Object> record = new ProducerRecord<>(topic, "key1", avroRecord);
        producer.send(record, new Callback() {
            @Override
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                if (e == null) {
                    System.out.println("Succes");
                } else {
                    System.out.println("It's not success");
                }
            }
        });
        System.out.println("sjajno");
        producer.flush();
        producer.close();
    }

    private static Properties getProperties() {
        Properties properties = new Properties();
        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty("value.serializer", KafkaAvroSerializer.class.getName());
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.setProperty("schema.registry.url", "http://localhost:8081");
        return properties;
    }

    private static String parseAvro() {
        String schema = "{\n" +
                "  \"type\": \"record\",\n" +
                "  \"name\": \"FootbalData\",\n" +
                "  \"namespace\": \"sendData\",\n" +
                "  \"fields\": [\n" +
                "    {\n" +
                "      \"name\": \"count\",\n" +
                "      \"type\": \"int\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"filters\",\n" +
                "      \"type\": \"null\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"areas\",\n" +
                "      \"type\": {\n" +
                "        \"type\": \"array\",\n" +
                "      \"items\": [\n" +
                "        {\n" +
                "          \"name\": \"Country\",\n" +
                "          \"type\": \"record\",\n" +
                "          \"fields\": [\n" +
                "            {\n" +
                "              \"name\": \"id\",\n" +
                "              \"type\": \"int\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"name\": \"name\",\n" +
                "              \"type\": \"string\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"name\": \"countryCode\",\n" +
                "              \"type\": \"string\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"name\": \"flag\",\n" +
                "              \"type\": \"null\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"name\": \"parentAreaId\",\n" +
                "              \"type\": \"int\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"name\": \"parentArea\",\n" +
                "              \"type\": \"string\"\n" +
                "            }\n" +
                "          ]\n" +
                "        }\n" +
                "        ]\n" +
                "\n" +
                "   }\n" +
                "      }\n" +
                "  ]\n" +
                "}";
        return schema;
    }

    public static FootballData storeData(URL url) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("X-Auth-Token", "0148d0534d34417a8435572f1ab7f9ef");
        conn.connect();
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
        ObjectMapper mapper = new ObjectMapper();
        FootballData data = mapper.readValue(in.readLine(), FootballData.class);
        return data;
    }


}
