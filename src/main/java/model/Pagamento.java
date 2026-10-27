package model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Pagamento {

    private Long idPagamento;
    private Long idProcesso;
    private String numeroPd;
    private LocalDate dataPagamento;
    private BigDecimal valorPagamento;
    private Long idPd;

    public Long getIdPagamento() {
        return idPagamento;
    }

    public void setIdPagamento(Long idPagamento) {
        this.idPagamento = idPagamento;
    }

    public Long getIdProcesso() {
        return idProcesso;
    }

    public void setIdProcesso(Long idProcesso) {
        this.idProcesso = idProcesso;
    }

    public String getNumeroPd() {
        return numeroPd;
    }

    public void setNumeroPd(String numeroPd) {
        this.numeroPd = numeroPd;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public BigDecimal getValorPagamento() {
        return valorPagamento;
    }

    public void setValorPagamento(BigDecimal valorPagamento) {
        this.valorPagamento = valorPagamento;
    }

    public Long getIdPd() {
        return idPd;
    }

    public void setIdPd(Long idPd) {
        this.idPd = idPd;
    }
}