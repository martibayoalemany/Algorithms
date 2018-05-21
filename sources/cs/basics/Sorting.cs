/* 

namespace cs
{
    public class Sorting
    {
        public static void Main(string[] args)
        { 
            long result = new Sorting().execute();                
            ("%s: %d ", Sorting.class.getName(), result);
            
        }

        public void execute() {
            
        }

        private void merge_sort(Integer[] array, int lowerIndex, int higherIndex)
        {
            if (lowerIndex >= higherIndex)
                return;
            int tmp_size = higherIndex - lowerIndex + 1;
            if (tmp_size > 0 && (tmp_array == null || tmp_array.length < tmp_size))
                tmp_array = new Integer[higherIndex - lowerIndex + 1];

            int middle = (lowerIndex + higherIndex) / 2;
            merge_sort(array, lowerIndex, middle);
            merge_sort(array, middle + 1, higherIndex);

            // Copy up tho the middle
            for (int i = lowerIndex, j = 0; i <= middle && i < array.length; i++, j++)
                this.tmp_array[i] = array[i];

            // Compare the copy from 0..length with the elements from m.length and swap accordingly
            int i = lowerIndex, j = middle + 1, k = lowerIndex;
            while (i <= middle && j <= higherIndex && j < array.length)
                array[k++] = tmp_array[i] <= array[j] ? tmp_array[i++] : array[j++];

            while (i <= middle)
                array[k++] = tmp_array[i++];
        }
    }
}
*/