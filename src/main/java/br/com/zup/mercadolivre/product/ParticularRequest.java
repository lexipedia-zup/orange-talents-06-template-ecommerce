package br.com.zup.mercadolivre.product;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.ArrayList;
import java.util.List;

public class ParticularRequest {
    private String name;
    private String description;

    public ParticularRequest() {
    }

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public ParticularRequest(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public static List<Particular> toModelList(List<ParticularRequest> particularsRequest) {
        List<Particular> particulars = new ArrayList<>();

        for(ParticularRequest request:particularsRequest){
            particulars.add(request.toModel());
        }

        return particulars;
    }

    public Particular toModel(){
        return new Particular(this.name, this.description);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
