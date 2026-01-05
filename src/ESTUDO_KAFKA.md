# 📚 Estudo de Arquitetura Orientada a Eventos com Kafka

Este documento registra o aprendizado prático sobre mensageria, Kafka e Spring Boot.

## 🧠 Por que Mensageria?
Em arquiteturas modernas (Microsserviços), as aplicações não devem ser "grudadas" umas nas outras. Se o Sistema A chama o Sistema B diretamente e o B cai, o A trava.
A mensageria resolve isso através do **Desacoplamento Assíncrono**.

- **Sincrono (HTTP):** Eu te ligo, você atende na hora. Se não atender, eu falho.
- **Assíncrono (Kafka):** Eu te mando uma mensagem no WhatsApp. Você lê e processa quando puder/quiser.

### Benefícios Principais:
1.  **Resiliência:** Se o consumidor cair, a mensagem fica salva no Kafka até ele voltar. Nenhuma informação se perde.
2.  **Escalabilidade (Backpressure):** Se chegarem 10 mil pedidos por segundo, o Kafka segura a onda e o consumidor processa 1 por 1 sem derrubar o banco de dados.
3.  **Extensibilidade:** Podemos adicionar novos consumidores (ex: enviar email, gerar nota fiscal) sem mexer em quem envia a mensagem.

---

## 🛠️ Níveis de Implementação do Projeto

### Nível 1: Comunicação Básica (JSON) ✅
**Objetivo:** Trafegar objetos complexos entre Producer e Consumer.
- **Desafio:** O Kafka trafega *bytes*. O Java trafega *Objetos*.
- **Solução:** Implementamos `JsonSerializer` (Producer) e `JsonDeserializer` (Consumer).
- **Fluxo:** `Controller (REST)` -> `Producer` -> `Tópico Kafka` -> `Consumer`.

### Nível 2: Persistência (Banco de Dados) 🔄
**Objetivo:** O Consumer deve pegar a mensagem e salvar permanentemente.
- **Conceito:** Transformar um evento efêmero (mensagem) em estado persistente (registro no banco).
- **Tecnologias:** Spring Data JPA, Hibernate, Banco de Dados (H2/Postgres).

### Nível 3: Resiliência e Tratamento de Erros (Futuro) 🔜
**Objetivo:** O que acontece se o banco falhar ao salvar?
- **Conceito:** Dead Letter Queues (DLQ) e Retry Patterns.

---

## 📝 Glossário Rápido
- **Topic:** O "canal" onde as mensagens são publicadas (ex: `hello-topic`).
- **Producer:** Quem envia a mensagem.
- **Consumer:** Quem lê a mensagem.
- **Group ID:** A identidade do consumidor. Se mudar o ID, o Kafka trata como um novo leitor.
- **Offset:** O marcador de página. Indica qual foi a última mensagem lida.