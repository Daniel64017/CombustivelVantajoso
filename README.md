# Combustível Vantajoso 🚗⛽

O **Combustível Vantajoso** é um aplicativo móvel nativo desenvolvido para a plataforma Android utilizando a linguagem **Kotlin**. O objetivo do aplicativo é auxiliar motoristas a decidirem, de forma rápida e eficiente, qual combustível (Álcool ou Gasolina) oferece o melhor custo-benefício para o seu bolso ao abastecer o veículo.

O app adota o visual moderno do **Material Design 3**, estruturando toda a sua interface em uma única tela prática e intuitiva.

---

## 🚀 Funcionalidades

- **Valores Pré-configurados**: O aplicativo já vem pré-preenchido com valores de referência (Gasolina a R$ 6,20 e Álcool a R$ 4,00) para facilitar o uso imediato.
- **Entrada Personalizada**: O usuário pode alterar os preços por litro a qualquer momento e informar o valor total em dinheiro que deseja investir no abastecimento.
- **Lógica Inteligente de Decisão**: Aplica a fórmula tradicional de rendimento energético ($70\%$) para sugerir o combustível ideal.
- **Rendimento em Volume (Litros)**: O app calcula e exibe de forma clara quantos litros exatos de Gasolina e de Álcool você obteria com o dinheiro informado.
- **Validação Segura**: Possui tratamento de erros e exceções para evitar travamentos caso os campos fiquem vazios ou caracteres inválidos sejam digitados.

---

## 🧠 Como é feito o cálculo?

O aplicativo baseia-se na eficiência energética padrão, onde o Álcool (Etanol) rende em média $70\%$ do rendimento obtido com a Gasolina.

1. **Relação de Custo**:
   $$\text{Relação} = \frac{\text{Preço do Álcool}}{\text{Preço da Gasolina}}$$

2. **Critério de Escolha**:
   - Se a **Relação for menor que 0.70 ($70\%$)**, o **Álcool** é o combustível recomendado.
   - Se a **Relação for maior ou igual a 0.70 ($70\%$)**, a **Gasolina** é recomendada por oferecer melhor custo-benefício a longo prazo.

3. **Cálculo de Litros**:
   - $\text{Litros obtidos} = \frac{\text{Dinheiro a ser gasto (R\$)}}{\text{Preço do Litro do Combustível escolhido}}$

---

## 🛠️ Tecnologias Utilizadas

- **Linguagem**: [Kotlin](https://kotlinlang.org/)
- **Ferramenta de Desenvolvimento**: [Android Studio](https://developer.android.com/studio)
- **Interface Gráfica**: XML Layouts (usando `MaterialCardView`, `ScrollView` e componentes do Material 3)
- **Compilador e Build**: Gradle (Kotlin DSL - `.gradle.kts`)
- **Compatibilidade Mínima**: Android API 24 (Android 7.0 Nougat ou superior)
- **SDK Alvo**: API 34 (Android 14)

---

## 📦 Como Importar e Executar o Projeto

1. Faça o download ou clone este repositório no seu computador:
   ```bash
   git clone https://github.com/SEU_USUARIO/CombustivelVantajoso.git
