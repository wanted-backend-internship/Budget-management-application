<script setup>
import { defineProps } from 'vue';

const props = defineProps({
  results: Object,
});
</script>

<template></template>

<style scoped lang="scss">
@import '../../../assets/styles/common/container';
@import '../../../assets/styles/common/button';
@import '../../../assets/styles/common/input';
@import '../../../assets/styles/color/color';
@import '../../../assets/styles/common/typho';

.container-col {
  @include container(column, flex-start, flex-start, 100%, 100%);
  background-color: $background;
}

.content-col {
  @include container(column, flex-start, center, 100%, auto);
}
</style>
