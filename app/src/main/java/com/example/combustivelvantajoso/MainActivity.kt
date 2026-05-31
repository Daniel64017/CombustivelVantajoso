package com.example.combustivelvantajoso

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.card.MaterialCardView

/**
 * Atividade Principal do aplicativo Combustível Vantajoso.
 * Desenvolvido como projeto acadêmico para cálculo rápido do rendimento
 * e escolha ideal entre Álcool (Etanol) e Gasolina ao abastecer.
 */
class MainActivity : AppCompatActivity() {

    // Declaração dos componentes da interface de usuário (UI)
    private lateinit var editPrecoGasolina: EditText
    private lateinit var editPrecoAlcool: EditText
    private lateinit var editValorAbastecer: EditText
    private lateinit var btnCalcular: Button
    
    // Card e Textos de exibição do resultado
    private lateinit var cardResultado: MaterialCardView
    private lateinit var txtRecomendacao: TextView
    private lateinit var txtDetalhes: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicialização dos componentes vinculando-os ao arquivo XML de Layout
        inicializarComponentes()

        // Configuração do clique no botão para realizar os cálculos
        btnCalcular.setOnClickListener {
            executarCalculoVantagem()
        }
    }

    /**
     * Vincula as variáveis do Kotlin com as Views declaradas no XML activity_main.xml
     */
    private fun inicializarComponentes() {
        editPrecoGasolina = findViewById(R.id.editPrecoGasolina)
        editPrecoAlcool = findViewById(R.id.editPrecoAlcool)
        editValorAbastecer = findViewById(R.id.editValorAbastecer)
        btnCalcular = findViewById(R.id.btnCalcular)
        cardResultado = findViewById(R.id.cardResultado)
        txtRecomendacao = findViewById(R.id.txtRecomendacao)
        txtDetalhes = findViewById(R.id.txtDetalhes)
    }

    /**
     * Executa a validação das entradas do usuário e processa as contas para
     * definir qual combustível trará mais vantagem econômica e volume.
     */
    private fun executarCalculoVantagem() {
        // Captura o conteúdo dos campos em formato de String
        val precoGasolinaStr = editPrecoGasolina.text.toString().trim()
        val precoAlcoolStr = editPrecoAlcool.text.toString().trim()
        val valorAbastecerStr = editValorAbastecer.text.toString().trim()

        // Validação simples: impede que os campos fiquem em branco
        if (precoGasolinaStr.isEmpty() || precoAlcoolStr.isEmpty() || valorAbastecerStr.isEmpty()) {
            Toast.makeText(this, "Por favor, preencha todos os campos antes de calcular!", Toast.LENGTH_SHORT).show()
            return
        }

        try {
            // Conversão segura das Strings para valores decimais (Double)
            val precoGasolina = precoGasolinaStr.toDouble()
            val precoAlcool = precoAlcoolStr.toDouble()
            val valorAbastecer = valorAbastecerStr.toDouble()

            // Validação de segurança: os valores precisam ser positivos
            if (precoGasolina <= 0 || precoAlcool <= 0 || valorAbastecer <= 0) {
                Toast.makeText(this, "Os preços e o valor inseridos devem ser maiores que zero!", Toast.LENGTH_SHORT).show()
                return
            }

            // --- LÓGICA DE NEGÓCIO (CÁLCULO DA VANTAGEM) ---
            // Regra acadêmica/prática: O etanol rende em média 70% do que rende a gasolina.
            // Se (Preço do Álcool / Preço da Gasolina) < 0.70, abastecer com Álcool é mais vantajoso.
            val relacaoPrecos = precoAlcool / precoGasolina

            // Cálculo dos litros que seriam adquiridos com o dinheiro informado
            val litrosGasolina = valorAbastecer / precoGasolina
            val litrosAlcool = valorAbastecer / precoAlcool

            // Formatação amigável das variáveis numéricas para exibição na UI
            val relacaoPercentualFormatada = String.format("%.2f", relacaoPrecos * 100)
            val litrosGasolinaFormatados = String.format("%.2f", litrosGasolina)
            val litrosAlcoolFormatados = String.format("%.2f", litrosAlcool)
            val valorAbastecerFormatado = String.format("%.2f", valorAbastecer)

            // Definição da recomendação e estilo com base no resultado da conta
            if (relacaoPrecos < 0.70) {
                // Álcool é vantajoso
                txtRecomendacao.text = "Abasteça com ÁLCOOL!"
                txtRecomendacao.setTextColor(ContextCompat.getColor(this, R.color.success))

                txtDetalhes.text = buildString {
                    append("Com R$ ").append(valorAbastecerFormatado).append(" você compra:\n")
                    append("• ").append(litrosAlcoolFormatados).append(" litros de Álcool\n")
                    append("• ").append(litrosGasolinaFormatados).append(" litros de Gasolina\n\n")
                    append("Relação de preços atual: ").append(relacaoPercentualFormatada).append("%\n")
                    append("Como a relação é inferior a 70%, o Álcool é a opção mais econômica para o seu bolso!")
                }
            } else {
                // Gasolina é vantajosa
                txtRecomendacao.text = "Abasteça com GASOLINA!"
                txtRecomendacao.setTextColor(ContextCompat.getColor(this, R.color.primary))

                txtDetalhes.text = buildString {
                    append("Com R$ ").append(valorAbastecerFormatado).append(" você compra:\n")
                    append("• ").append(litrosGasolinaFormatados).append(" litros de Gasolina\n")
                    append("• ").append(litrosAlcoolFormatados).append(" litros de Álcool\n\n")
                    append("Relação de preços atual: ").append(relacaoPercentualFormatada).append("%\n")
                    append("Como a relação é igual ou superior a 70%, abastecer com Gasolina oferece melhor custo-benefício!")
                }
            }

            // Torna o painel de resultados visível na tela
            cardResultado.visibility = View.VISIBLE

        } catch (e: NumberFormatException) {
            // Captura erros de formatação de número caso o usuário digite algo inválido
            Toast.makeText(this, "Erro de digitação! Insira números válidos usando pontos ou vírgulas.", Toast.LENGTH_LONG).show()
        }
    }
}
