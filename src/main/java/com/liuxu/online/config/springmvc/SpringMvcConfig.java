package com.liuxu.online.config.springmvc;


import java.io.IOException;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * springmvc配置,此配置必须有，不然异常处理时不能解析json
 * @author lsx
 *
 */
@Configuration
public class SpringMvcConfig extends WebMvcConfigurerAdapter{

	/**
	 * 自定义ObjectMapper，处理springmvc序列化
	 * 
	 * @return
	 */
	@Bean
	public ObjectMapper jacksonObjectMapper() {

		ObjectMapper objectMapper = new ObjectMapper();
		
		//通过该方法对mapper对象进行设置，所有序列化的对象都将按改规则进行系列化 ,只对VO起作用，Map List不起作用
		//Include.Include.ALWAYS 默认 
		//Include.NON_DEFAULT 属性为默认值不序列化 
		//Include.NON_EMPTY 属性为 空（“”） 或者为 NULL 都不序列化 
		//Include.NON_NULL 属性为NULL 不序列化 
		objectMapper.setSerializationInclusion(Include.NON_NULL);
		objectMapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
			@Override
			public void serialize(Object value, JsonGenerator jg, SerializerProvider sp)
					throws IOException, JsonProcessingException {
				// 所有null字段，重写为空字符串
//				jg.writeString("");
				sp.getDefaultNullKeySerializer();
			}
		});
		objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		return objectMapper;
	}
	
	@Bean(name = "messageSource")
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource rb = new ReloadableResourceBundleMessageSource();
		rb.setBasenames("classpath:message","classpath:message-auth","classpath:message-cms","classpath:message-cms-server");//为国际化和参数校验指定一个目录
		return rb;
		
	}
	
//	@Bean
//	public Filter logFilter() {
//		CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
//		filter.setIncludeQueryString(true);
//		filter.setIncludePayload(true);
//		filter.setMaxPayloadLength(5120);
//		return filter;
//	}

    /**
     * 构建方法级别验证
     * @return
     */
	@Bean
	public MethodValidationPostProcessor methodValidationPostProcessor(LocalValidatorFactoryBean localValidatorFactoryBean) {
		MethodValidationPostProcessor postProcessor = new MethodValidationPostProcessor();
		postProcessor.setValidator(localValidatorFactoryBean);
		return postProcessor;
	}

//	/**
//	 * 文件上传临时路径
//	 */
//	@Bean
//	MultipartConfigElement multipartConfigElement() {
//		MultipartConfigFactory factory = new MultipartConfigFactory();
//		factory.setLocation("/gomeo2o/data/uploadTemp");
//		return factory.createMultipartConfig();
//	}

//	@Override
//	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//		
//		/*converters.add(new ByteArrayHttpMessageConverter());
//		
//		FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
//		
//		List<MediaType> list = new ArrayList<MediaType>();
//		list.add(MediaType.APPLICATION_JSON);
//		converter.setSupportedMediaTypes(list);
//		
//		SerializerFeature[] features = new SerializerFeature[] { SerializerFeature.WriteNullListAsEmpty,
//				SerializerFeature.WriteNullBooleanAsFalse };
//		 
//		converter.setFeatures(features);
//		
//		converters.add(converter);*/
//	}
	
	//解决对象为空不抛异常，默认抛异常，如果为空时，重写空字符串jg.writeString("");就不用此方法处理
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(jacksonMessageConverter());
		super.configureMessageConverters(converters);
	}

	private MappingJackson2HttpMessageConverter jacksonMessageConverter() {
		MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		messageConverter.setObjectMapper(mapper);
		return messageConverter;
	}
	
	
	@Bean(name ="validator")
	public LocalValidatorFactoryBean localValidatorFactoryBean(){
		LocalValidatorFactoryBean lfb = new LocalValidatorFactoryBean();
		lfb.setValidationMessageSource(messageSource());
        //初始化ValidatorFactory
//		lfb.afterPropertiesSet();
		return lfb;
	}
	
	@Override
	public Validator getValidator() {
 		return localValidatorFactoryBean();
	}
//	// 添加拦截器
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
////		registry.addInterceptor(authInterceptor).addPathPatterns("/**");
////		registry.addInterceptor(logInterceptor).addPathPatterns("/**");
//	}
//	@Override
//	public MessageCodesResolver getMessageCodesResolver() {
//		return super.getMessageCodesResolver();
//	}
}
