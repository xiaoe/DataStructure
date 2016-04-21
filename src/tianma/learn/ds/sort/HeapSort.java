package tianma.learn.ds.sort;

/**
 * 堆排序（Heap Sort）
 * <p>
 * 将排序序列构成一个最大堆。此时整个序列的最大值就是堆顶的根节点，然后将其移走（将其与堆数组的末尾元素交换，此时数组末尾元素就是最大值），然后将剩余的n-
 * 1个序列重新构造成一个堆，重复此操作，便能得到有序序列
 * <p>
 * 堆排序的时间复杂度为O(nlogn)，由于堆排序对原始记录的排序状态并不敏感，所以它的最好、最坏、平均时间复杂度均为O(nlogn);
 * 性能高于希尔排序以及插入排序等。<br>
 * 由于初始构建堆所需要的比较次数较多，所以堆排序不适合待排序序列个数少的情况
 * 
 * @author Tianma
 *
 */
public class HeapSort implements Sorter {

	@Override
	public int[] sort(int[] arr) {
		heapSort(arr);
		return arr;
	}

	private void heapSort(int[] arr) {
		int len = arr.length;
		int i;
		// 将当前序列构成最大堆
		for (i = len / 2 - 1; i >= 0; i--) {
			// 由最后一个非叶子节点开始从后往前遍历，将数组后半部分构成最大堆
			adjustHeap(arr, i, len - 1);
		}

		// 将最大堆堆顶元素与数组末尾元素交换，并将前n-1序列重新构造成最大堆,重复n-1次
		for (i = len - 1; i >= 1; i--) {
			swap(arr, 0, i); // 将堆顶元素和当前未经排序的子序列的最后一个元素进行交换
			adjustHeap(arr, 0, i - 1); // 将arr[0...i-1](前i个元素)重新调整为最大堆
		}
	}

	/**
	 * 已知数组arr[begin...end]中的记录，除了arr[begin]之外的序列满足最大堆的定义；本方法通过调整关键字arr[begin]，
	 * 使arr[begin...end]称为一个最大堆；即将数组arr中以begin为起点，以end为终点的子序列变成最大堆
	 * 
	 * @param arr
	 *            参与构造最大堆的序列
	 * @param begin
	 *            构造最大堆的起始位置
	 * @param end
	 *            构造最大堆的结束位置
	 */
	private void adjustHeap(int[] arr, int begin, int end) {
		int tmp = arr[begin];
		int j;
		for (j = 2 * begin + 1; j <= end; j = 2 * j + 1) {
			// j=2*begin+1表示j对应二叉树节点的左孩子
			if (j + 1 <= end && arr[j] < arr[j + 1]) {
				// 如果当前节点的右孩子存在且左孩子的值小于右孩子
				j++; // j为左右孩子较大记录的下标
			}
			if (tmp >= arr[j]) // tmp的值已经大于arr[j]，则调整完毕，跳出循环
				break;
			arr[begin] = arr[j]; // 当前根节点并未均大于左右节点(如果有的话)，重新给当前根节点赋值
			begin = j; // begin指向新的可能需要进行最大堆调整的子树的根节点
		}
		arr[begin] = tmp;
	}

}
