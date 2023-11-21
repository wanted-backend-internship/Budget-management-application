<script setup lang="ts">
import { ref } from 'vue';
import { setBudgetTotal } from '../../api/BudgetApi.ts';
import router from '../../router';

const budgetTotal = ref(0);

const handleBudget = async () => {
  const budgetTotalData = {
    budgetTotal: budgetTotal.value,
  };

  try {
    const response = await setBudgetTotal(budgetTotalData);
    if (response.status == 200) {
      let userInfo = localStorage.getItem('userInfo');
      userInfo = userInfo ? JSON.parse(userInfo) : {};
      userInfo.budgetTotal = response.data.budgetTotal;
      localStorage.setItem('userInfo', JSON.stringify(userInfo));
      await router.push('/budgets/setting');
    }
  } catch (error) {
    console.log(error);
  }
};
</script>

<template>
  <div class="container-col">
    <form class="content-col">
      <div class="form-col">
        <div class="font_title">이번 달의 예산은?</div>
        <div class="font-label">이번 달의 목표 금액을 적어주세요.</div>
        <input v-model="budgetTotal" class="input" type="number" />
        <div class="content-row">
          <button
            @click.prevent="handleBudget"
            type="submit"
            class="button-blue"
          >
            등록
          </button>
        </div>
      </div>
    </form>
  </div>
</template>

<style scoped lang="scss">
@import '../../assets/styles/common/container';
@import '../../assets/styles/common/button';
@import '../../assets/styles/common/input';
@import '../../assets/styles/color/_color';
@import '../../assets/styles/common/typho';

.container-col {
  @include container(column, flex-start, flex-start, 100%, 100%);
  background-color: $background;
}

.content-col {
  @include container(column, flex-start, center, 100%, auto);
}

.content-row {
  @include container(row, flex-start, center, 100%, auto);
}

.form-col {
  @include container(column, flex-start, flex-start, 310px, auto);
  padding-left: 5px;
}

.button-blue {
  @include button-blue(100px, 35px, 20px 15px 0 0);
  @include pre200(14px, whitesmoke);
}

.input {
  @include input(280px, 32px, 18px, 0 10px, 8px);
  margin-bottom: 10px;
}

.font_title {
  @include pre300(30px, $black);
  margin: 30px 0 20px 0;
}

.font-label {
  @include pre100(16px, $black);
  margin: 10px 0 7px 0;
}
</style>
