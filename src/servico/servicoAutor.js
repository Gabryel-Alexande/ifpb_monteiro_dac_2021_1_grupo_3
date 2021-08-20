import {http} from './configuracoes'

export default{
    lista:() => {return http.get("pagamentos/metodo")},
    adiciona:(metodo) => {return http.post("pagamentos/criar_metodo",metodo)}
}
