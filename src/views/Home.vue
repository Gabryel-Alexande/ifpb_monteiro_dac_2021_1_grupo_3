<template>
    <div class="container-fluid my-4">
        <label for="input-produto-editora">Pagamentos</label>
        <table class="table">
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Nome</th>
                    <th scope="col">Excluir</th>
                    <th scope="col">Editar</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="metodo in metodos" :key="metodo.idMetodoPagamento">
                    <th scope="row">{{metodo.idMetodoPagamento}}</th>
                    <td>{{ metodo.nomeDoPagamento }}</td>
                    <td>
                        <button id="bnt_excluir" v-on:click="deletarMetodo(metodo)" class="btn  btn-primary" type="button">Excluir</button>
                    </td>
                    <td>
                        <router-link class="btn  btn-primary" :to="'/edit/' + metodo.idMetodoPagamento">Editar</router-link>
                    </td>
                </tr>
            </tbody>
        </table>
    
    </div>
</template>

<script>
import servicoAutor from '../servico/servicoAutor'
//import RemoverMetodo from './views/RemoverMetodo.vue'
import axios from 'axios'
export default {
    name: 'Home',
    data() {
        return {
            metodos: this.$store.state.metodos

        }
    },
    created() {
        console.log("Created.")
        servicoAutor.lista().then(
            dado => {
                this.$store.state.metodos = dado.data
                this.metodos = this.$store.state.metodos
            })
    },
    methods: {
        deletarMetodo: function(valor) {
            if (confirm("Quer realmente Excluir: " + valor.nomeDoPagamento + " ?")) {
                axios.delete("http://localhost:8080/pagamentos/" + valor.idMetodoPagamento)
                this.$router.push("/add");
            }

        }


    }

}
</script>
