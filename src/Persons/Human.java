package Persons;

abstract class Human {
	private int age;
	private String name;

	public Human(String name, int age) {
		this.age = age;
		this.name = name;
	}

	public int getAge() {
		return this.age;
	}
	
	public String getName() {
		return name;
	}

	abstract void introduceMyself();
}
