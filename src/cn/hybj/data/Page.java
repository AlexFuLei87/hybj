package cn.hybj.data;

import java.util.List;

public class Page {

	@SuppressWarnings("unchecked")
	private List data;

	private int size;

	private int now;

	private int up;

	private int down;

	private int first;

	private int last;

	private int pageSum;

	private int rowSum;

	public Page() {
	}

	public Page(int now, int size, int rowSum) {
		this.rowSum = rowSum;
		this.size = size;
		this.pageSum = rowSum == 0 ? 0 : ((rowSum - 1) / size + 1);
		this.first = rowSum == 0 ? 0 : 1;
		this.last = rowSum == 0 ? 0 : this.pageSum;
		this.now = rowSum == 0 ? 0 : now;
		this.now = (rowSum > 0 && now < 1) ? 1 : now;
		this.now = this.now > this.pageSum ? this.pageSum : this.now;
		this.up = rowSum == 0 ? 0 : ((this.now > 1) ? (this.now - 1) : 1);
		this.down = rowSum == 0 ? 0 : ((this.now < this.pageSum) ? (this.now + 1) : this.pageSum);
	}

	@SuppressWarnings("unchecked")
	public List getData() {
		return data;
	}

	@SuppressWarnings("unchecked")
	public void setData(List data) {
		this.data = data;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getNow() {
		return now;
	}

	public void setNow(int now) {
		this.now = now;
	}

	public int getUp() {
		return up;
	}

	public void setUp(int up) {
		this.up = up;
	}

	public int getDown() {
		return down;
	}

	public void setDown(int down) {
		this.down = down;
	}

	public int getFirst() {
		return first;
	}

	public void setFirst(int first) {
		this.first = first;
	}

	public int getLast() {
		return last;
	}

	public void setLast(int last) {
		this.last = last;
	}

	public int getPageSum() {
		return pageSum;
	}

	public void setPageSum(int pageSum) {
		this.pageSum = pageSum;
	}

	public int getRowSum() {
		return rowSum;
	}

	public void setRowSum(int rowSum) {
		this.rowSum = rowSum;
	}

}
