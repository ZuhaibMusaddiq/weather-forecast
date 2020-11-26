<template>
  <div id="app">
    <div id="nav">
      <router-link to="/">Home</router-link> |
      <router-link to="/about">About</router-link>      
    </div>
    <router-view />
  </div>
</template>

<style lang="scss">
  @import url(https://unpkg.com/bootstrap@4.1.0/dist/css/bootstrap.min.css);
</style>
