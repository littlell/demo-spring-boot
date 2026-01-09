# Kafka 增量式学习步骤

## 学习目标
通过增量式学习，逐步掌握 Kafka 的核心概念和使用方法，每次只专注于一个功能点，便于理解和实践。

## 步骤规划

### 步骤 1：环境配置与基础依赖
**学习目标**：搭建 Kafka 开发环境，配置基础依赖

**Kafka 配置**：
1. 安装并启动本地 Kafka 和 Zookeeper
2. 验证 Kafka 服务是否正常运行

**代码修改**：
1. 确保 `pom.xml` 中包含 Spring Kafka 依赖
2. 配置 `application.yml` 连接本地 Kafka 服务器

**测试方法**：
- 运行 `kafka-topics.sh --list --bootstrap-server localhost:9092` 查看主题列表

### 步骤 2：创建第一个 Kafka 主题
**学习目标**：了解 Kafka 主题概念，实现主题自动创建

**Kafka 配置**：
- 在 `application.yml` 中添加 Kafka Admin 配置

**代码修改**：
1. 修改 `KafkaConfig.java`，添加 `KafkaAdmin` Bean
2. 创建 `NewTopic` Bean，定义测试主题

**测试方法**：
- 启动应用，查看日志确认主题是否创建成功
- 使用 `kafka-topics.sh --describe --topic topic.test --bootstrap-server localhost:9092` 查看主题详情

### 步骤 3：实现简单生产者
**学习目标**：了解 Kafka 生产者概念，实现消息发送

**Kafka 配置**：
- 无需额外配置

**代码修改**：
1. 在 `KafkaConfig.java` 中配置生产者工厂
2. 创建 `KafkaTemplate` Bean
3. 编写生产者测试类 `ProducerTest.java`

**测试方法**：
- 运行测试类，发送测试消息
- 使用 `kafka-console-consumer.sh --topic topic.test --bootstrap-server localhost:9092 --from-beginning` 接收消息

### 步骤 4：实现简单消费者
**学习目标**：了解 Kafka 消费者概念，实现消息接收

**Kafka 配置**：
- 无需额外配置

**代码修改**：
1. 在 `KafkaConfig.java` 中配置消费者工厂
2. 创建 `ConcurrentKafkaListenerContainerFactory` Bean
3. 编写 `KafkaConsumer.java`，添加 `@KafkaListener` 注解

**测试方法**：
- 启动应用
- 运行生产者测试，查看消费者是否接收到消息

### 步骤 5：JSON 序列化与反序列化
**学习目标**：了解 Kafka 序列化机制，实现 JSON 对象的发送和接收

**Kafka 配置**：
- 无需额外配置

**代码修改**：
1. 创建 `User.java` 实体类
2. 配置 JSON 序列化的生产者工厂
3. 配置 JSON 反序列化的消费者工厂
4. 添加发送和接收 User 对象的代码

**测试方法**：
- 运行测试类发送 User 对象
- 查看消费者是否成功接收并解析 JSON 对象

### 步骤 6：消费者组管理
**学习目标**：了解 Kafka 消费者组概念，实现多组消费

**Kafka 配置**：
- 无需额外配置

**代码修改**：
1. 为同一主题添加多个消费者组
2. 实现不同消费者组的差异化处理逻辑

**测试方法**：
- 发送消息，查看不同消费者组是否都能接收到消息
- 观察不同消费者组的处理结果

### 步骤 7：Kafka 事务支持
**学习目标**：了解 Kafka 事务概念，实现事务性消息发送

**Kafka 配置**：
- 在 `application.yml` 中添加事务相关配置

**代码修改**：
1. 配置事务性生产者工厂
2. 添加 `KafkaTransactionManager` Bean
3. 实现事务性消息发送代码

**测试方法**：
- 运行测试类发送多条事务性消息
- 验证消息是否全部成功或全部失败

### 步骤 8：监控与最佳实践
**学习目标**：了解 Kafka 监控方法，学习最佳实践

**Kafka 配置**：
- 启用 Kafka JMX 监控

**代码修改**：
- 添加日志记录，便于监控和调试

**测试方法**：
- 使用 JConsole 连接 Kafka 进程，查看监控指标
- 结合日志分析 Kafka 运行情况

## 学习建议

1. **循序渐进**：每次只完成一个步骤，确保理解后再进行下一步
2. **实践优先**：每个步骤都要实际运行代码，观察结果
3. **查阅文档**：遇到问题时，查阅官方文档和相关资料
4. **调试日志**：启用 Kafka 日志，观察内部运行机制
5. **对比学习**：对比不同配置下的运行结果，理解配置的作用

## 每个步骤的详细实现

### 步骤 1：环境配置与基础依赖

**pom.xml 依赖配置**：
```xml
<dependency>
    <groupId>org.springframework.kafka</groupId>
    <artifactId>spring-kafka</artifactId>
</dependency>
```

**application.yml 配置**：
```yaml
spring:
  kafka:
    bootstrap-servers: localhost:9092
```

### 步骤 2：创建第一个 Kafka 主题

**application.yml 配置**：
```yaml
spring:
  kafka:
    admin:
      properties:
        bootstrap.servers: localhost:9092
```

**KafkaConfig.java 修改**：
```java
@Bean
public KafkaAdmin kafkaAdmin() {
    Map<String, Object> configs = new HashMap<>();
    configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    return new KafkaAdmin(configs);
}

@Bean
public NewTopic testTopic() {
    return TopicBuilder.name("topic.test")
            .partitions(1)
            .replicas(1)
            .build();
}
```

### 步骤 3：实现简单生产者

**KafkaConfig.java 修改**：
```java
@Bean
public Map<String, Object> producerConfigs() {
    Map<String, Object> props = new HashMap<>();
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    return props;
}

@Bean
public ProducerFactory<String, String> producerFactory() {
    return new DefaultKafkaProducerFactory<>(producerConfigs());
}

@Bean
public KafkaTemplate<String, String> kafkaTemplate() {
    return new KafkaTemplate<>(producerFactory());
}
```

**ProducerTest.java 创建**：
```java
@SpringBootTest
public class ProducerTest {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Test
    public void send() {
        kafkaTemplate.send("topic.test", "hello kafka");
    }
}
```

### 步骤 4：实现简单消费者

**KafkaConfig.java 修改**：
```java
@Bean
public Map<String, Object> consumerConfigs() {
    Map<String, Object> props = new HashMap<>();
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    props.put(ConsumerConfig.GROUP_ID_CONFIG, "group.test");
    props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    return props;
}

@Bean
public ConsumerFactory<String, String> consumerFactory() {
    return new DefaultKafkaConsumerFactory<>(consumerConfigs());
}

@Bean
public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory());
    return factory;
}
```

**KafkaConsumer.java 创建**：
```java
@Component
public class KafkaConsumer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = "topic.test", groupId = "group.test")
    public void receiveMessage(String message) {
        logger.info("Received message: {}", message);
    }
}
```

### 步骤 5：JSON 序列化与反序列化

**User.java 创建**：
```java
public class User implements Serializable {
    private Long id;
    private String name;
    private String email;
    private Integer age;
    // Getters and Setters
}
```

**KafkaConfig.java 修改**：
```java
@Bean
public ProducerFactory<String, User> jsonProducerFactory() {
    Map<String, Object> props = new HashMap<>();
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
    return new DefaultKafkaProducerFactory<>(props);
}

@Bean
public KafkaTemplate<String, User> jsonKafkaTemplate() {
    return new KafkaTemplate<>(jsonProducerFactory());
}
```

### 步骤 6：消费者组管理

**KafkaConsumer.java 修改**：
```java
@KafkaListener(topics = "topic.test", groupId = "group.test.2")
public void receiveMessageGroup2(String message) {
    logger.info("[Group 2] Received message: {}", message);
}
```

### 步骤 7：Kafka 事务支持

**KafkaConfig.java 修改**：
```java
@Bean
public ProducerFactory<String, User> transactionalProducerFactory() {
    Map<String, Object> props = new HashMap<>();
    props.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "transactional-producer-1");
    props.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);
    // 其他配置
    return new DefaultKafkaProducerFactory<>(props);
}

@Bean
public KafkaTransactionManager<String, User> kafkaTransactionManager() {
    return new KafkaTransactionManager<>(transactionalProducerFactory());
}
```

### 步骤 8：监控与最佳实践

**application.yml 配置**：
```yaml
spring:
  kafka:
    producer:
      properties:
        log4j.logger.org.apache.kafka=INFO
    consumer:
      properties:
        log4j.logger.org.apache.kafka=INFO
```

## 总结

通过以上 8 个增量式学习步骤，你将逐步掌握 Kafka 的核心概念和使用方法。每个步骤都专注于一个功能点，便于理解和实践。建议你按照顺序完成每个步骤，并在实际运行中观察结果，加深理解。

在学习过程中，你可以随时查阅 Kafka 官方文档和 Spring Kafka 文档，获取更详细的信息。同时，结合日志分析和监控工具，深入理解 Kafka 的运行机制。