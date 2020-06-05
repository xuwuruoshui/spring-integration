package top.xuwuruoshui.springes.config;

/*@Configuration*/
public class RestClientConfig /* extends AbstractElasticsearchConfiguration*/ {

/*    private String connect = "localhost:9200";
    private long connectTimeout = 5000;
    private long socketTimeout = 5000;

    @Override
    @Bean
    public RestHighLevelClient elasticsearchClient() {

        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo(connect)
                .withConnectTimeout(connectTimeout)
                .withSocketTimeout(socketTimeout)
                .build();


        return RestClients.create(clientConfiguration).rest();
    }

    @Bean
    public ElasticsearchRestTemplate elasticsearchRestTemplate(){
        return new ElasticsearchRestTemplate(elasticsearchClient());
    }*/
}