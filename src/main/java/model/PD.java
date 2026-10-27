package model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PD {

    private Long idPd;
    private String numeroPd;
    private LocalDate dataPagamento;
    private BigDecimal valorTotal;

    public Long getIdPd() {
        return idPd;
    }

    public void setIdPd(Long idPd) {
        this.idPd = idPd;
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

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }
}