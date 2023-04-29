package br.com.graficaplantao.rest.api.domain.transportadoras;

import br.com.graficaplantao.rest.api.domain.transportadoras.dto.request.AtualizacaoTransportadoraDTO;
import br.com.graficaplantao.rest.api.domain.transportadoras.dto.request.NovaTransportadoraDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "transportadoras")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Transportadora {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cnpj;

    private String razao_social;

    private String nome_fantasia;

    private String fone;

    public Transportadora(NovaTransportadoraDTO dados) {
        this.cnpj = dados.cnpj();
        this.razao_social = dados.razao_social();
        this.nome_fantasia = dados.nome_fantasia();
        this.fone = dados.fone();
    }

    public void update(AtualizacaoTransportadoraDTO dados) {
        if(dados.cnpj() != null) {
            this.cnpj = dados.cnpj();
        }
        if(dados.razao_social() != null) {
            this.razao_social = dados.razao_social();
        }
        if(dados.nome_fantasia() != null) {
            this.nome_fantasia = dados.nome_fantasia();
        }
        if(dados.fone() != null) {
            this.fone = dados.fone();
        }
    }
}
