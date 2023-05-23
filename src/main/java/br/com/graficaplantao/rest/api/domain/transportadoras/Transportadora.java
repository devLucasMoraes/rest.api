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

    @JoinColumn(name = "cnpj")
    private String cnpj;

    @JoinColumn(name = "razao_social")
    private String razaoSocial;

    @JoinColumn(name = "nome_fantasia")
    private String nomeFantasia;

    @JoinColumn(name = "fone")
    private String fone;

    public Transportadora(NovaTransportadoraDTO dados) {
        this.cnpj = dados.cnpj();
        this.razaoSocial = dados.razaoSocial();
        this.nomeFantasia = dados.nomeFantasia();
        this.fone = dados.fone();
    }

    public void update(AtualizacaoTransportadoraDTO dados) {
        if(dados.cnpj() != null) {
            this.cnpj = dados.cnpj();
        }
        if(dados.razaoSocial() != null) {
            this.razaoSocial = dados.razaoSocial();
        }
        if(dados.nomeFantasia() != null) {
            this.nomeFantasia = dados.nomeFantasia();
        }
        if(dados.fone() != null) {
            this.fone = dados.fone();
        }
    }
}
