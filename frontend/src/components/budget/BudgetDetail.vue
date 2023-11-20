<script setup lang="ts">
import { getBudgets } from '../../api/BudgetApi.ts';
import { onMounted, ref } from 'vue';
import { Chart, registerables } from 'chart.js';

Chart.register(...registerables);

const budgetData = ref([]);
const storedUserInfo = localStorage.getItem('userInfo');
const userInfo = storedUserInfo ? JSON.parse(storedUserInfo) : null;

const handleBudgets = async () => {
  try {
    const response = await getBudgets();
    budgetData.value = response.data;
    createChart();
  } catch (error) {
    console.error(error);
  }
};

const createChart = () => {
  const OTHER_THRESHOLD = 10;
  let otherPercentage = 0;
  let filteredData = budgetData.value.filter(item => {
    if (item.percentage <= OTHER_THRESHOLD) {
      otherPercentage += item.percentage;
      return false;
    }
    return true;
  });

  if (otherPercentage > 0) {
    filteredData.push({ category: '기타', percentage: otherPercentage });
  }

  const ctx = (
    document.getElementById('budgetChart') as HTMLCanvasElement
  ).getContext('2d');
  const chartData = {
    labels: filteredData.map(item => item.category),
    datasets: [
      {
        data: filteredData.map(item => item.percentage),
        backgroundColor: [
          '#255CC7',
          '#4AB0D4',
          '#DB2F7E',
          '#DE932B',
          '#AEAEB2',
        ],
      },
    ],
  };

  new Chart(ctx, {
    type: 'pie',
    data: chartData,
  });
};

onMounted(handleBudgets);
</script>

<template>
  <div class="container-col">
    <div class="content-col">
      <div class="font-title">
        <span style="color: #db2f7e">{{ userInfo.username }}</span>
        <span>님의 이번달 예산이에요</span>
      </div>
      <canvas id="budgetChart"></canvas>
    </div>
  </div>
</template>

<style scoped lang="scss">
@import '../../assets/styles/common/container';
@import '../../assets/styles/common/button';
@import '../../assets/styles/color/_color';
@import '../../assets/styles/common/typho';

.container-col {
  @include container(column, center, center, 100%, 100%);
}

.content-col {
  @include container(column, center, center, 80%, 100%);
}

.font-title {
  @include pre300(24px, $black);
  margin: 30px 0 20px 0;
}
</style>
