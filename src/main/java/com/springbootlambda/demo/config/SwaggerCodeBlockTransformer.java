/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.springbootlambda.demo.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.springdoc.core.SwaggerUiConfigParameters;
import org.springdoc.core.SwaggerUiConfigProperties;
import org.springdoc.core.SwaggerUiOAuthProperties;
import org.springdoc.webmvc.ui.SwaggerIndexPageTransformer;
import org.springdoc.webmvc.ui.SwaggerWelcomeCommon;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.resource.ResourceTransformerChain;
import org.springframework.web.servlet.resource.TransformedResource;

/**
 *
 * @author danielcardenas
 */
public class SwaggerCodeBlockTransformer
       extends SwaggerIndexPageTransformer {

    public SwaggerCodeBlockTransformer(SwaggerUiConfigProperties swaggerUiConfig, SwaggerUiOAuthProperties swaggerUiOAuthProperties, SwaggerUiConfigParameters swaggerUiConfigParameters, SwaggerWelcomeCommon swaggerWelcomeCommon) {
        super(swaggerUiConfig, swaggerUiOAuthProperties, swaggerUiConfigParameters, swaggerWelcomeCommon);
    }
  // < constructor >
  @Override
  public Resource transform(HttpServletRequest request,
                            Resource resource,
                            ResourceTransformerChain transformer)
                            throws IOException {
      if (resource.toString().contains("swagger-ui.css")) {
          final InputStream is = resource.getInputStream();
          final InputStreamReader isr = new InputStreamReader(is);
          try (BufferedReader br = new BufferedReader(isr)) {
              final String css = br.lines().collect(Collectors.joining());
              final byte[] transformedContent = css.replace("old", "new").getBytes();
              return  new TransformedResource(resource, transformedContent);
          } // AutoCloseable br > isr > is
      }
      return super.transform(request, resource, transformer);
  }

}
