<script setup lang="ts">
import TheLayout from './layouts/TheLayout.vue';
</script>

<template>
  <div class="container-col">
    <TheLayout />
  </div>
</template>

<style lang="scss">
@import './assets/styles/common/container.scss';
.container-col {
  @include container(column, flex-start, flex-start, 100%, 100vh);
}
</style>
