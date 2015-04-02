


package com.example.repositories;

import com.example.ExampleHttpClient;
import com.example.resources.Mandate;
import com.google.common.collect.ImmutableMap;

import java.io.IOException;

public class MandateRepository {
    private ExampleHttpClient httpClient;

    public MandateRepository(ExampleHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    
        
        public Mandate create() throws IOException {
        
            throw new IllegalStateException("Not implemented!");
        
        }
    
        
        public Mandate list() throws IOException {
        
            throw new IllegalStateException("Not implemented!");
        
        }
    
        
        public Mandate get(String identity) throws IOException {
        
            ImmutableMap.Builder<String, String> params = ImmutableMap.builder();

            
                params.put("identity", identity);
            

            return httpClient.get("/mandates/:identity", params.build(), "mandates", Mandate.class);
        
        }
    
        
        public Mandate update(String identity) throws IOException {
        
            throw new IllegalStateException("Not implemented!");
        
        }
    
        
        public Mandate cancel(String identity) throws IOException {
        
            throw new IllegalStateException("Not implemented!");
        
        }
    
        
        public Mandate reinstate(String identity) throws IOException {
        
            throw new IllegalStateException("Not implemented!");
        
        }
    
}
