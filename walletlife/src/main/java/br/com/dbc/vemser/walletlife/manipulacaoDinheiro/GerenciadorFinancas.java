//package br.com.dbc.vemser.walletlife.manipulacaoDinheiro;
//
//import br.com.dbc.vemser.walletlife.enumerators.TipoDespesaEReceita;
//import br.com.dbc.vemser.walletlife.modelos.*;
//import br.com.dbc.vemser.walletlife.service.DespesaService;
//import br.com.dbc.vemser.walletlife.service.InvestimentoService;
//import br.com.dbc.vemser.walletlife.service.ReceitaService;
//
//import java.util.HashMap;
//
//public class GerenciadorFinancas implements IManipularFinancas, IImpressao {
//
////    private final Usuario usuario;
//
//    private HashMap<Integer, Despesa> despesas;
//    private DespesaService despesaService;
//
//    private HashMap<Integer, Investimento> investimentos;
//
//    private InvestimentoService investimentoService;
//
//    private HashMap<Integer, Receita> receitas;
//    private ReceitaService receitaService;
//
//    private double totalReceita;
//
//    private double totalDespesas;
//
//    private double totalInvestimento;
//
////    public GerenciadorFinancas(Usuario usuario) {
////        this.despesas = new HashMap<>();
////        this.receitas = new HashMap<>();
////        this.investimentos = new HashMap<>();
////        this.usuario = usuario;
////
////        this.investimentoService = new InvestimentoService();
////        this.despesaService = new DespesaService();
////        this.receitaService = new ReceitaService();
////        popularBanco();
////    }
//
//    public void popularBanco() {
//        Integer idUsuario = usuario.getId();
//        int id = 0;
//        for (Investimento investimento : investimentoService.listar(idUsuario)) {
//            investimentos.put(id, investimento);
//            totalInvestimento += investimento.getValor();
//            ++id;
//        }
//
//        id = 0;
//        for (Despesa despesa : despesaService.listarDespesa(idUsuario)) {
//            this.despesas.put(id, despesa);
//            totalDespesas += despesa.getValor();
//            ++id;
//        }
//
//        id = 0;
//        for (Receita receita : receitaService.listar(idUsuario)) {
//            this.receitas.put(id, receita);
//            totalReceita += receita.getValor();
//            ++id;
//        }
//
//    }
//
//    @Override
//    public double calcularTotal(HashMap<?, ?> lista) {
//        Double total = 0.0;
//        for (AbstractMovimentoDinheiro<TipoDespesaEReceita> despesa : despesas.values()) {
//            total += despesa.getValor();
//        }
//
//        return total;
//    }
//
//    @Override
//    public double calcularReceitaTotal() {
//        totalReceita = calcularTotal(receitas);
//        return totalReceita;
//    }
//
//    @Override
//    public double calcularInvestimentos() {
//        totalInvestimento = calcularTotal(investimentos);
//        return totalInvestimento;
//    }
//
//    @Override
//    public double calcularDespesa() {
//        totalDespesas = calcularTotal(despesas);
//        return totalDespesas;
//    }
//
//    public HashMap<Integer, Despesa> getDespesas() {
//        return despesas;
//    }
//
//    public void addDespesa(Despesa despesa) {
//        this.despesas.put(despesas.size(), despesa);
//        totalDespesas += despesa.getValor();
//        despesaService.adicionarDespesa(despesa);
//    }
//
//    public void updateValorDespesa(int id, double valor) {
//        Despesa despesa = this.despesas.get(id);
//        this.totalDespesas -= despesa.getValor();
//        despesa.setValor(valor);
//        this.totalDespesas += valor;
//        this.despesas.replace(id, despesas.get(id));
//        this.despesaService.editarDespesa(despesa.getId(), despesa);
//    }
//
//    public void updateDescricaoDespesa(int id, String descricao) {
//        Despesa despesa = this.despesas.get(id);
//        despesa.setDescricao(descricao);
//        this.despesas.replace(id, despesa);
//        this.despesaService.editarDespesa(despesa.getId(), despesa);
//    }
//
//    public boolean deleteDespesa(int id) {
//        this.totalDespesas -= despesas.get(id).getValor();
//        boolean removido = despesas.replace(id, despesas.get(id)) != null;
//
//        if (removido)
//            despesaService.removerDespesa(despesas.get(id).getId());
//
//        return removido;
//    }
//
//    public HashMap<Integer, Investimento> getInvestimentos() {
//        return investimentos;
//    }
//
//    public void addInvestimento(Investimento investimento) {
//        this.investimentos.put(investimentos.size(), investimento);
//        this.totalInvestimento += investimento.getValor();
//        investimentoService.adicionarInvestimento(investimento);
//    }
//
//    public void updateValorInvestimento(int id, double valor) {
//        Investimento investimento = investimentos.get(id);
//        this.totalInvestimento -= investimento.getValor();
//        investimento.setValor(valor);
//        this.totalInvestimento += investimento.getValor();
//        this.investimentos.replace(id, investimento);
//        this.investimentoService.editarInvestimento(investimento.getId(), investimento);
//    }
//
//    public void updateDescricaoInvestimento(int id, String descricao) {
//        Investimento investimento = investimentos.get(id);
//        investimento.setDescricao(descricao);
//        this.investimentos.replace(id, investimento);
//        investimentoService.editarInvestimento(investimento.getId(), investimento);
//    }
//
//    public boolean deleteInvestimento(int id) {
//        Investimento investimento = investimentos.get(id);
//        this.totalInvestimento -= investimento.getValor();
//        boolean removido = this.investimentos.replace(id, investimento) != null;
//        if (removido)
//            this.investimentoService.removerInvestimento(investimento.getId());
//        return removido;
//    }
//
//    public HashMap<Integer, Receita> getReceitas() {
//        return receitas;
//    }
//
//    public void addReceita(Receita receita) {
//        receitas.put(receitas.size(), receita);
//        totalReceita += receita.getValor();
//        receitaService.adicionarReceita(receita);
//    }
//
//    public void updateValorReceita(int id, double valor) {
//        Receita receita = receitas.get(id);
//        this.totalReceita -= receita.getValor();
//        receita.setValor(valor);
//        this.totalReceita += receita.getValor();
//        this.receitas.replace(id, receita);
//        this.receitaService.editarReceita(receita.getId(), receita);
//    }
//
//    public void updateDescricaoReceita(int id, String descricao) {
//        Receita receita = this.receitas.get(id);
//        receita.setDescricao(descricao);
//        this.receitas.replace(id, receita);
//        this.receitaService.editarReceita(receita.getId(), receita);
//    }
//
//    public boolean deleteReceita(int id) {
//        Receita receita = receitas.get(id);
//        totalReceita -= receita.getValor();
//        boolean removido = receitas.replace(id, receita) != null;
//
//        if (removido)
//            receitaService.removerReceita(receita.getId());
//
//        return removido;
//    }
//
//    public double getValorTotalReceita() {
//        return this.totalReceita;
//    }
//
//    public void setValorTotalReceita(double totalReceita) {
//        this.totalReceita = totalReceita;
//    }
//
//    public double getValorTotalDespesas() {
//        return this.totalDespesas;
//    }
//
//    public void setValorTotalDespesas(double totalDespesas) {
//        this.totalDespesas = totalDespesas;
//    }
//
//    public double getValorTotalInvestimento() {
//        return totalInvestimento;
//    }
//
//    public void setTotalInvestimentos(double totalInvestimento) {
//        this.totalInvestimento = totalInvestimento;
//    }
//
//    public String relatorioGeral() {
//        return String.format("""
//                ----------------------------------
//                | Tipos: | Quantidade | Valor
//                ----------------------------------
//                | Despesa | %d | %5.2f
//                ----------------------------------
//                | Investimento |  %d  | %5.2f
//                ----------------------------------
//                | Receita | %d | %5.2f
//                ----------------------------------
//                """, despesas.size(), totalDespesas, investimentos.size(), totalInvestimento, receitas.size(), totalReceita
//        );
//    }
//
//    @Override
//    public void imprimir() {
//        System.out.println(
//                "GerenciadorFinancas {" +
//                        "despesas=" + despesas +
//                        ", investimentos=" + investimentos +
//                        ", receitas=" + receitas +
//                        ", totalReceita=" + totalReceita +
//                        ", totalDespesas=" + totalDespesas +
//                        ", totalInvestimento=" + totalInvestimento +
//                        ", usuario=" + usuario +
//                        '}'
//        );
//    }
//
//}
