package app.configs;

import app.enums.Status;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class ConverterConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StatusConverter());
    }

    class StatusConverter implements Converter<String, Status> {

        public Status convert(String source) {
            try {
                return Status.create(source);
            } catch (Exception e) {
                return null;
            }
        }


    }

}