import axiosInstance from './interceptor/RequestInterceptor.ts';
export const setBudgetTotal = async (budgetTotalData: {
  budgetTotal: number;
}) => {
  const response = await axiosInstance.patch('/budgets/me', budgetTotalData);
  console.log(response.data);
  return response;
};

export const createBudgets = async (
  budgetData: Array<{ category: string; percentage: number }>,
) => {
  const response = await axiosInstance.post('/budgets', budgetData);
  console.log(response.data);
  return response;
};

export const getBudgets = async () => {
  const response = await axiosInstance.post('/budgets/me');
  console.log(response.data);
  return response;
};
