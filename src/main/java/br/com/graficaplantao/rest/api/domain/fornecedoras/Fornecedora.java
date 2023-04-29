package br.com.graficaplantao.rest.api.domain.fornecedoras;

import br.com.graficaplantao.rest.api.domain.fornecedoras.dto.request.AtualizacaoFornecedoraDTO;
import br.com.graficaplantao.rest.api.domain.fornecedoras.dto.request.NovaFornecedoraDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "fornecedoras")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Fornecedora {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cnpj;

    private String razao_social;

    private String nome_fantasia;

    private String fone;

    public Fornecedora(NovaFornecedoraDTO dados) {
        this.cnpj = dados.cnpj();
        this.razao_social = dados.razao_social();
        this.nome_fantasia = dados.nome_fantasia();
        this.fone = dados.fone();
    }

    public void update(AtualizacaoFornecedoraDTO dados) {
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
