<template>
<div>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
      <div class="container-fluid"> 
        <div class="collapse navbar-collapse" id="navbarNav">
          <ul class="navbar-nav">
            <li class="nav-item">
              <router-link class="nav-link" to="/"> Home </router-link>
            </li>
            <li class="nav-item">
              <router-link class="nav-link" to="/add"> Adicionar Metodos de Pagamentos </router-link>
            </li>
          </ul>
        </div>
      </div>
    </nav>
</div>
</template>

<script>

export default {
  name: 'Navegacao',
  data() {
      return {};
  }
}
</script>