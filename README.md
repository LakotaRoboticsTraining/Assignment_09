# Lesson 9: Inheritance

Goal: Create a subclass that extends a parent class, reuse its fields/methods, and add or replace behavior.

Time: About 40–50 minutes

You will learn:

- Parent (superclass) vs child (subclass)
```java
The extends keyword
```

- What the child inherits
- super(...) to call the parent constructor
```java
@Override to replace a parent method
```

Before this lesson: Lessons 7–8 (classes, objects, constructors).

### Why this matters for robots

```java
WPILib is built on inheritance. ShootCommand extends Command. DriveTrain implements/extends subsystem types. You do not reinvent "a command"; you extend the library class and fill in initialize, execute, end.
```

### The big idea

Inheritance = a new class is a more specific version of an existing class.

```java
Motor              <- general: side + speed + report()
```



Read FalconMotor extends Motor as: "a FalconMotor is a Motor."

### extends

ppublic class Motor {

String side;

double speed;

public Motor(String side, double speed) {

this.side = side;

this.speed = speed;

}

public void report() {

System.out.println(side + " speed = " + speed);

}

}

public class FalconMotor extends Motor {

int canId;

public FalconMotor(String side, double speed, int canId) {

super(side, speed);   // call Motor's constructor first

this.canId = canId;

}

}



FalconMotor inherits side, speed, and report() unless you replace them.

```java
FFalconMotor left = new FalconMotor("left", 0.4, 1);
```

left.report();           // inherited from Motor

System.out.println(left.canId);



### super

super(...) calls the parent constructor. It must be the first statement in the child constructor when you use it.

You use it so the parent can set up *its* fields (side, speed) before the child sets canId.

### @Override

The child can replace a parent method:

```java
ppublic class FalconMotor extends Motor {
```

int canId;

public FalconMotor(String side, double speed, int canId) {

super(side, speed);

this.canId = canId;

}

@Override

public void report() {

System.out.println(side + " Falcon (CAN " + canId + ") speed = " + speed);

}

}



```java
@Override tells Java (and teammates): "I meant to replace the parent method." If you typo the name, the compiler can catch it.
```

### “Is a” vs “has a”

Robot code uses both. Do not extend just to share a helper — prefer a field if it is not truly "a kind of."

### Common mistakes

1. Forgetting super(...) when the parent has no no-arg constructor

2. super not first in the constructor

3. Wrong mental model — subclass is not a copy-paste folder; it is the parent type plus extras

4. Overriding without matching the method signature (name + parameters)

5. Deep inheritance trees — keep it shallow for now (one parent is enough)

## Try it yourself

`Game` and `Arcade` are provided. You write the subclasses.

### Challenge 1 â€” Extend

Create `VideoGame extends Game` and `Pinball extends Game`.  
Each constructor takes `(String name, int year, String type)` and calls `super(...)`.

### Challenge 2 â€” Inherited members

In `Main`, store subclass objects in `Game` variables, add them to an `Arcade` library, and call `listGameLibrary()`.

### Challenge 3 â€” Override play

`@Override play()` on both subclasses:

- VideoGame output must include `video game` and the game name
- Pinball output must include `pinball` and the game name

Call `play()` on both from `Main`.

### Check your understanding

```java
1. What does extends mean?
2. What does super

---
```

(side, speed) do?

```java
3. Why use @Override?
```

4. Is FalconMotor a Motor?

---

Answers

1. This class is a subclass of the named parent.

2. Runs the parent constructor to set up parent fields.

3. Marks that you are replacing a parent method; helps catch mistakes.

4. Yes — that is the "is a" relationship.

### Looking ahead

In Lesson 10, you will use polymorphism: treating a FalconMotor as a Motor so one list or one method can work with many specific types.

Lesson complete. When you can extend a class, call super, and override a method, you are ready for Lesson 10.
