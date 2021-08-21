<template>
  <main class="container">
    <div>
      <header>
        <div class="jumbotron mb-0">
          <h1 v-if="isEditing === false" class="display-4">
            Adicionar Pagamentos
          </h1>
          <h1 v-if="isEditing === true" class="display-4">
            Editar Metodo de Pagamentos
          </h1>
        </div>
      </header>

      <div class="card">
        <form class="card-body" @submit.prevent>
          <div class="form-group">
            <label for="nome" class="form-label"
              >Nome Metodo de Pagamento :</label
            >
            <input
              type="text"
              required
              id="nome"
              class="form-control"
              v-model="metodo.nomeDoPagamento"
            />
            <div class="invalid-feedback"></div>
          </div>

          <button
            class="botao"
            v-if="isEditing === false"
            type="submit"
            value="Enviar"
            v-on:click="enviarMetodoDePagamento()"
          >
            Cadastrar
          </button>
          <button
            class="botao"
            v-if="isEditing === true"
            type="submit"
            value="Enviar"
            v-on:click="enviarMetodoDePagamento()"
          >
            Salvar
          </button>
        </form>
      </div>
    </div>
  </main>
</template>

<script>
import servicoPag from "../servico/servicoPag";
import axios from "axios";

export default {
  name: "AdicionaPag",
  props: ["metodoId"],
  data() {
    return {
      isEditing: false,

      metodo: {
        idMetodoPagamento: undefined,

        nomeDoPagamento: "",
      },
    };
  },
  mounted() {
    this.fetchMetodoPagamento();
  },

  methods: {
    enviarMetodoDePagamento: function () {
      if (this.metodo.idMetodoPagamento !== -1) {
        axios.put(
          "http://localhost:8080/pagamentos/editar-metodo/" +
            this.metodo.idMetodoPagamento,
          this.metodo
        );
        this.$router.push("/");
      } else {
        if (this.metodo.nomeDoPagamento != "") {
          servicoPag.adiciona(this.metodo).then((dado) => {
            console.log(dado.data),
              this.$store.commit("ADICIONA_PAGAMENTO", this.metodo);
          });
          console.log("Metodo  adicionado: " + this.metodo.nomeDoPagamento);
          this.$router.push("/");
        } else {
          console.log("ERRO: Metodo com valor vazio ");
        }
      }
    },

    fetchMetodoPagamento: function () {
      if (this.metodoId) {
        this.isEditing = true;
        let dadoBruto = axios.get(
          "http://localhost:8080/pagamentos/editar-metodo/" + this.metodoId
        );

        dadoBruto.then((dado) => {
          this.metodo = dado.data;
        });
        this.metodo.idMetodoPagamento = this.metodoId;

        //console.log(pag.nomeDoPagamento)
      }
    },
  },
};
</script>
<style>
button {
  background-color: #a50a98;
  color: white;
  border-radius: 30px;
  width: 150px;
  height: 40px;
  border-color: white;
}
</style>