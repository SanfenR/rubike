
/**
 * 数据类型
 * 
 * dart中的所有变量都存储对该值的引用
 * 
 */
void main() {

  // /**
  //  * 基本类型
  //  */
  // String nameDef = 'sanfen';
  // int yearDef = 1997;
  // double antennaDiameterDef = 3.7;
  // bool boyDef = true;
  // List flybyObjectsDef = ['Jupiter', 'Saturn', 'Uranus', 'Neptune'];
  // Map imageDef = {
  //   'tags': ['saturn'],
  //   'url': '//path/to/saturn.jpg'
  // };
  // print('$nameDef\n$yearDef\n$boyDef\n$antennaDiameterDef\n$flybyObjectsDef\n' + '$imageDef');

  /**
   * 所有未初始化的变量的初始值为null
   */
  // int num;
  // print(num);



  /**
   * var AND dynamic
   */
  // 字符串 String
  // var name = 'sanfen';
  // // 数字 int
  // var year = 1997;
  // // 浮点数 double
  // var antennaDiameter = 3.7;
  // //  布尔 bool
  // var boy = true;
  // //  列表 list
  // var flybyObjects = ['Jupiter', 'Saturn', 'Uranus', 'Neptune'];
  // //  哈希表 map
  // var image = {
  //   'tags': ['saturn'],
  //   'url': '//path/to/saturn.jpg'
  // };
  // print('$name\n$year\n$boy\n$antennaDiameter\n$flybyObjects\n' + '$image');

  /**
   * Dart 通过在变量名前加上数据类型来支持 类型检查
   */
  // var nameTest = 'Sanfen';
  // nameTest = 1;
  // print(nameTest);

  /**
   * dynamic 关键字声明没有静态类型的变量被隐式声明为动态。
   */
  // dynamic nameTest1 = 'sanfen';
  // nameTest1 = 1;
  // print(nameTest1); 

  /**
   * final and const
   * const比final更加严格。
   * final只是要求变量在初始化后值不变，final无法在编译时知道这个变量的值；
   * 而const所修饰的是编译时常量，我们在编译时就已经知道了它的值
   */
  // final v1 = 12;
  // print(v1);
  // v1 = 13;
  // print(v1);

  // const v2 = 12
  // v2 = 13;
  // print(v2);
  // const pi = 3.14;
  // const area = pi*12*12;
  // print("The output is ${area}");


  
}