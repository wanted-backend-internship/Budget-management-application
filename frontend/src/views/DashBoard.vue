<script setup lang="ts">
import { onMounted, ref } from 'vue';
import { Chart, registerables } from 'chart.js';
import { todayExpenditure } from '../api/ExpenditureApi.ts';

Chart.register(...registerables);
const chartCanvas = ref(null);
let myChart = null;

let userInfo = localStorage.getItem('userInfo');
userInfo = userInfo ? JSON.parse(userInfo) : {};

const fetchTodayExpenditure = async () => {
  try {
    const response = await todayExpenditure();
    const expenditures = response.data;
    const data = {
      labels: expenditures.map(e => e[0]),
      datasets: [
        {
          label: '카테고리',
          data: expenditures.map(e => e[1]),
          backgroundColor: 'rgba(54, 162, 235, 0.2)',
          borderColor: 'rgba(54, 162, 235, 1)',
          borderWidth: 1,
          barPercentage: 0.5,
        },
      ],
    };

    const options = {
      indexAxis: 'y', // 가로 막대 그래프를 만들기 위해 'y' 축으로 설정
      scales: {
        x: {
          beginAtZero: true,
        },
      },
      maintainAspectRatio: false, // 컨테이너에 따라 차트 크기 조절
    };

    if (myChart) {
      myChart.destroy();
    }

    myChart = new Chart(chartCanvas.value.getContext('2d'), {
      type: 'bar',
      data: data,
      options: options,
    });
  } catch (error) {
    console.error(error);
  }
};

onMounted(fetchTodayExpenditure);
</script>

<template>
  <div class="container-col">
    <div class="content-col">
      <div class="font_head">이번달 예산</div>
      <div class="font_money">{{ userInfo.budgetTotal }} 원</div>
    </div>
    <div
      class="content-col"
      style="background-color: transparent; padding: 0 20px"
    >
      <div class="font_title">오늘의 지출</div>
      <div style="height: 300px; width: 90%">
        <canvas ref="chartCanvas"></canvas>
      </div>
    </div>
  </div>
</template>

<style scoped lang="scss">
@import '../assets/styles/common/container';
@import '../assets/styles/color/_color.scss';
@import '../assets/styles/common/typho.scss';

.container-col {
  @include container(column, flex-start, flex-start, 100%, 100%);
}

.content-col {
  @include container(column, flex-start, flex-start, 100%, 130px);
  background-color: $blue;
}

.font_head {
  @include pre100(16px, whitesmoke);
  margin: 25px 0 0 20px;
  width: 100%;
}

.font_money {
  @include pre200(40px, whitesmoke);
  margin-left: 20px;
}

.font_title {
  @include pre300(30px, $black);
  margin: 30px 0 20px 0;
}
</style>
