# Week 8 Quiz & Concept Answers

## Quiz Questions (MCQ)

1. **C — Polymorphism.** A `Document` reference calling `render()` removes the
   if-else type checks entirely.
2. **A, B.** Car is-a Vehicle; Rectangle is-a Shape. (D is a has-a relationship;
   C is questionable at best.)
3. **C — Runtime method dispatch.** The JVM picks the method based on the
   object's actual type at runtime, not the reference type.
4. **C — Inheritance-based polymorphism.**
5. **A, C, D.** Book extends inherited behavior; LibraryItem defines the common
   contract; a DVD can be used through a LibraryItem reference. (B is wrong —
   DVD *overrides* the period, it doesn't share it.)
6. **A, C.** An `Animal` reference to a `Dog` still calls `Dog.makeSound()`;
   Dog's version is the overridden one. (Animal's is the base behavior, not
   "specialized"; derived classes can also add brand-new methods.)
7. **C** — a new `WalletPayment` drops into the existing processing loop with
   zero changes to it.
8. **A** — fake is-a couples unrelated classes and makes the design rigid.
9. **A, C.** One sender handles all channels; new channels plug in without
   touching the sender. (Logic lives in subclasses, not "centralized"; each
   overrides `send()` differently.)
10. **A, B, C.** (D is backwards — polymorphic collections make adding types
    easier, not harder.)

## Concept Questions (short answers)

1. **Reuse + extend/modify.** A subclass gets all base-class behavior for free
   (inheritance), can *extend* it via `super.method()` plus extra logic, or
   *replace* it by overriding. Example: a `DiscountedProduct extends Product`
   reuses `getPrice()` storage but overrides it to apply a discount — the
   pricing service needs no changes.
2. **'Is-a'.** X is-a Y means every X can be used anywhere a Y is expected. It
   matters because inheritance promises substitutability — if the relationship
   isn't genuine (e.g., `Stack extends Vector`), inherited operations make no
   sense and the design lies to its users.
3. **Overriding.** A subclass redefines an inherited method with the same
   signature. Example: `Employee.calculateSalary()` — `SalariedEmployee`
   returns monthly pay, `HourlyEmployee` returns rate × hours, `Intern` returns
   a stipend. Payroll iterates `Employee[]` and each object computes its own way.
4. **Runtime polymorphism / dynamic dispatch.** The compiler only checks that
   the method exists on the reference's declared type; at runtime the JVM looks
   at the object's actual class and invokes the most-derived override — via the
   virtual method table (vtable). So `Animal a = new Dog(); a.makeSound()` runs
   Dog's version.
5. **Polymorphic collections.** e.g. `Payment[] payments = {new CardPayment(), new WalletPayment()}`.
   The loop `for (Payment p : payments) p.calculateFee()` handles every type
   uniformly — one code path, new types drop in without touching the loop.
6. **Polymorphism vs if-else chains.** Conditionals require the caller to know
   every type and must be edited for each new one; polymorphism pushes the
   decision into the objects themselves. Less coupling, no duplicated dispatch
   logic, open for extension.
7. **Extensibility.** `PaymentProcessor` holds `Payment[]` and calls
   `p.calculateFee()`. Adding `WalletPayment extends Payment` with its own
   `calculateFee()` requires zero changes to the processor — the loop already
   works. That's the Open/Closed principle in miniature.
8. **Inherited vs overridden.** Inherit when the base behavior is already
   correct (e.g., `Car` inherits `Vehicle.getRegistration()`); override when the
   type needs specialized behavior (e.g., `ElectricCar` overrides
   `refuel()`/`recharge()` semantics).
9. **Inheritance only for reuse.** Faking is-a to steal code couples unrelated
   classes: the subclass inherits meaningless methods, breaks substitutability,
   and inherits every future change to the parent. Prefer composition
   (has-a) when you only want reuse.
10. **VehicleRental example.** Common: rental days, base daily rate,
    `calculateCost()`, customer details — lives in `VehicleRental`. Specialized:
    `CarRental` adds insurance tiers, `TruckRental` adds cargo-weight surcharge
    and loading fees — each overrides `calculateCost()`. The billing loop treats
    them all as `VehicleRental`.
