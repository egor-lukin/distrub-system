//Определить сумму из произведений элементов каждого столбца  матрицы.
#include <mpi.h>
#include <stdio.h>

int main (int argc, char* argv[])
{
  int lengthi = 3;
  int lengthj = 3;
  int matrix[3][3] = {
    {1, 3, 4},
    {3, 4, 2},
    {5, 6, 3}
  };

  int drobSum = 0, Result;
  int i, j;

  int rank, size;

  MPI_Init (&argc, &argv);
  MPI_Comm_rank (MPI_COMM_WORLD, &rank);
  MPI_Comm_size (MPI_COMM_WORLD, &size);

  for (j = rank; j < lengthj; j += size)
  {
    int mul = 1;
    for (i = 0; i < lengthi; i += 1)
    {
      mul *= matrix[i][j];
    }
    drobSum += mul;
    printf("sum = %d, column = %d\n", mul, j);
  }

  MPI_Reduce(&drobSum, &Result, 1, MPI_INT, MPI_SUM, 0, MPI_COMM_WORLD);

  if (rank == 0){
    printf("Result = %d", Result);
  }

  MPI_Finalize();
  return 0;
}
