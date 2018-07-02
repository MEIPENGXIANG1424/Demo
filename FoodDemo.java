package Sshuzu;

import java.util.Scanner;

public class FoodDemo {

	public static void main(String args[]) {
		String[] names = new String[4]; // 订餐人
		String[] dishMegs = new String[4];// 所定餐品信息
		int[] times = new int[4];// 送餐时间
		String[] addresses = new String[4];// 送餐地址
		int[] states = new int[4];// 订单状态 0：已锁定 1：已完成
		double[] sumPrices = new double[4];// 总金额

		// 初始化2条订单信息
		names[0] = "张晴";
		dishMegs[0] = "红烧带鱼1份";
		times[0] = 12;
		addresses[0] = "天成路209号";
		states[0] = 1;
		sumPrices[0] = 24.0;

		names[1] = "张晴";
		dishMegs[1] = "鱼香肉丝2份";
		times[1] = 12;
		addresses[1] = "天成路209号";
		states[1] = 0;
		sumPrices[1] = 76.0;

		// 数据主体:一组餐品信息
		String[] dishNames = { "红烧带鱼", "鱼香肉丝", "时令蔬菜" };
		double[] prices = { 38.0, 18.0, 10.0 };
		int[] praiseNums = new int[3];

		// 循环（do- while）
		Scanner input = new Scanner(System.in);
		int num = -1;
		do {
			// 循环操作：
			// 显示主菜单
			System.out.println("****************");
			System.out.println("1.我要订餐");
			System.out.println("2.查看餐袋");
			System.out.println("3.删除订单");
			System.out.println("4.签收订单");
			System.out.println("5.我要点赞");
			System.out.println("6.退出系统");
			System.out.println("****************");
			// 提示用户输入功能编号，并执行相应的功能
			System.out.println("请选择：");
			int choose = input.nextInt();

			boolean isAdd = false; // 记录找打一个为空的位置，可以插入 false：没有找到
			switch (choose) {
			case 1:
				// 我要订餐
				System.out.println("*****我要订餐*****");

				// 找要插入的位置，循环遍历订餐人第一个为空的位置
				// 如果找到了，则此位置就是要插入数据的位置
				// 没找到，则显示餐袋已满。
				for (int i = 0; i < names.length; i++) {

					if (names[i] == null) {
						isAdd = true;

						// 2执行添加数据操作
						// a 显示所有可供选择的餐品信息
						System.out.println("序号\t餐品名\t单价\t点赞数");
						for (int j = 0; j < dishNames.length; j++) {
							String price = prices[j] + "元";
							String praise = praiseNums[j] + "赞";
							System.out.println((j + 1) + "\t" + dishNames[j]
									+ "\t" + price + "\t" + praise);

						}
						// 用户选择餐品编号以及份数

						System.out.print("请选择所定餐品的序号：");
						int chooseDish = input.nextInt();
						System.out.print("请选择所定餐数：");
						int number = input.nextInt();
						String dishMeg = dishNames[chooseDish - 1] + " "
								+ number + "份";
						// b输入订餐人姓名
						System.out.print("请输入订餐人姓名：");
						String name = input.next();
						// c输入送餐时间（10~20点之间整点送餐）
						System.out.print("请输入送餐时间（10~20整点送餐）:");
						int time = input.nextInt();
						// 当输入的信息有误时，重复输入
						while (time < 10 || time > 20) {
							System.out.println("对不起，您的输入有误，请重新输入10~20直接的整数");
							time = input.nextInt();
						}
						// d输入送餐地址
						System.out.println("请输入订餐地址：");
						String address = input.next();

						// e计算总金额 总金额=餐品单价*份数
						double sumPrice = prices[chooseDish - 1] * number;
						// 送餐费：餐品总金额达到50时，免6元餐费
						double deliCharge = sumPrice >= 50 ? 0.0 : 6.0;
						// 3显示订单信息
						System.out.println("订餐成功！");
						System.out.println("您定的是：" + dishMeg);
						System.out.println("订餐人：" + name);
						System.out.println("送餐时间：" + time + "点");
						System.out.println("送餐地址：" + address);
						System.out.println("餐费：" + sumPrice + ",送餐费："
								+ deliCharge + "元");
						System.out.println("总金额：" + (sumPrice + deliCharge)
								+ "元");

						// 4向数组保存数据
						names[i] = name;
						dishMegs[i] = dishMeg;
						times[i] = time;
						addresses[i] = address;
						sumPrices[i] = sumPrice + deliCharge;

						break;
					}
				}
				if (!isAdd) {
					System.out.println("对不起，您的餐袋已满");
				}

				break;
			case 2:
				// 查看餐袋
				System.out.println("******查看餐袋******");
				System.out.println("序号\t订餐人\t所定餐品信息\t送餐时间\t送餐地址\t\t总金额\t状态");
				for (int i = 0; i < names.length; i++) {
					if (names[i] != null) {
						String time = times[i] + "点";
						String sumPrice = sumPrices[i] + "元";
						String state = states[i] == 0 ? "已预订" : "已完成";
						System.out
								.println((i + 1) + "\t" + names[i] + "\t"
										+ dishMegs[i] + "\t" + time + "\t"
										+ addresses[i] + "\t" + sumPrice + "\t"
										+ state);
					}
				}

				break;
			case 3:
				// 删除订单
				// 输入要删除的订单序号（从1开始）
				System.out.println("******删除订单******");

				System.out.println("请输入要删除的订单序号：");
				int delId = input.nextInt();
				int delIndex = -1;
				// 循环差找这条订单
				boolean isDelFind = false;
				for (int i = 0; i < names.length; i++) {
					// 找到此订单，经签收：执行删除操作
					// 找到此订单吗，并且未签收：提示信息
					if (names[i] != null && states[i] == 1 && delId == i + 1) {
						delIndex = i;
						isDelFind = true;
						break;
					} else if (names[i] != null && states[i] == 0
							&& delId == i + 1) {

						System.out.println("您选择的订单未签收，不能删除！");
						isDelFind = true;
						break;
					}
				}

				// 未找到此订单：提示信息
				if (!isDelFind) {
					System.out.println("对不起，此条订单不存在！");
				}

				//           
				// 删除操作（循环移位）
				if (delId != -1) {
					for (int i = delId + 1; i <= names.length - 1; i++) {

						names[i - 1] = names[i];
						dishMegs[i - 1] = dishMegs[i];
						times[i - 1] = times[i];
						addresses[i - 1] = addresses[i];
						states[i - 1] = states[i];

					}
					// 最后一位清空
					names[names.length - 1] = null;
					dishMegs[names.length - 1] = null;
					times[names.length - 1] = 0;
					addresses[names.length - 1] = null;
					states[names.length - 1] = 0;

					System.out.println("信息已被删除！");
				}

				// 从要删除的

				break;
			case 4:
				// 签收订单
				// 要求用书输入一个签收的订单序号
				System.out.println("******签收订单******");
				System.out.println("请输入要签收的订单序号：");
				boolean isFind = false; // 记录是否找到了订单
				int signOrderId = input.nextInt();

				// 查找这条订单（循环遍历）
				for (int i = 0; i < names.length; i++) {
					// 1.找到，状态是以完成（以经签收完毕）
					// 2.找到，状态是已预订（没有完成签收）
					if (names[i] != null && states[i] == 1
							&& signOrderId == i + 1) {
						System.out.println("对不起，您选择的订单已经完成签收，不能再次签收！");
						isFind = true;
						break;
					} else if (names[i] != null && states[i] == 0
							&& signOrderId == i + 1) {

						states[i] = 1; // 状态值改为1；
						System.out.println("订单签收成功！");

						isFind = true;

					}
				}

				// 3.没找到：提示信息没有找到
				if (!isFind) {
					System.out.println("对不起，此订单不存在！");
				}

				break;
			case 5:
				// 我要点赞\
				System.out.println("******我要点赞******");
				
				System.out.println("序号\t餐品名\t单价\t点赞数");
				for (int j = 0; j < dishNames.length; j++) {
					String price = prices[j] + "元";
					String praise = praiseNums[j] + "赞";
					System.out.println((j + 1) + "\t" + dishNames[j]
							+ "\t" + price + "\t" + praise);

				}
				//输入要点赞的餐品序号
				System.out.println("请输入要点赞的餐品序号：");
				int dishId=input.nextInt();
				//该序号的餐品点赞数加1
				praiseNums[dishId-1]++;
				System.out.println("点赞成功！");
				System.out.println(dishNames[dishId-1]+ " "+praiseNums[dishId-1]+"赞");
				break;
			case 6:
				// 退出系统
				break;
			default:
				break;

			}
			// 当用输入的功能 编号为1~5之外的数字时，退出循环，否则则会提示输入0返回
			if (choose < 1 || choose > 5) {
				break;
			} else {
				// 提示输入0返回
				// 循环条件
				// 输入0
				System.out.println("输入0返回:");
				num = input.nextInt();
			}
		} while (num == 0);
		System.out.println("谢谢使用，欢迎下次光临！");
	}

}
