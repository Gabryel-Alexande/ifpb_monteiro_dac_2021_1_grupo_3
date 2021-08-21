<template>
  <!-- <Cabecalho></Cabecalho> -->
  <h1 v-text="msg"></h1>
  <Navegacao></Navegacao>
  <router-view />
</template>

<script>
// import Cabecalho from "./views/Cabecalho";
import Navegacao from "./views/Navegacao.vue";

export default {
  name: "App",
  data() {
    return {
      msg: "Bem vindo a Tela de Metodos de Pagamentos",
    };
  },
  components: {
    // Cabecalho,
    Navegacao,
  },
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>