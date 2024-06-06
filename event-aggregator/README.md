---
title: Event Aggregator
category: Messaging
language: en
tag:
    - Decoupling
    - Event-driven
    - Messaging
    - Publish/subscribe
    - Reactive
---

## Also known as

* Event Channel
* Event Central
* Message Hub

## Intent

The Event Aggregator design pattern aims to reduce the direct dependencies between multiple systems and components that need to interact by introducing a single component, the Event Aggregator, that receives events from multiple sources and distributes them to multiple listeners.

## Explanation

Real-world example

> An analogous real-world example of the Event Aggregator pattern can be found in a newsroom. In a newsroom, multiple reporters and correspondents are constantly gathering news and reporting on various events from different locations. Instead of each reporter communicating directly with every news editor or producer, all their reports and updates are channeled through a central news desk. This news desk acts as the Event Aggregator, receiving all incoming reports, filtering and prioritizing them, and then dispatching the relevant information to the appropriate editors or producers. This centralization simplifies the process, reduces direct dependencies, and ensures that news updates are managed efficiently and effectively.

In Plain Words

> Event Aggregator is an event mediator that collects events from multiple sources and delivers them to registered observers.

**Programmatic Example**

King Joffrey sits on the iron throne and rules the seven kingdoms of Westeros. He receives most of his critical information from King's Hand, the second in command. King's hand has many close advisors himself, feeding him with relevant information about events occurring in the kingdom.

In our programmatic example, we demonstrate the implementation of an event aggregator pattern. Some of the objects are event listeners, some are event emitters, and the event aggregator does both.

```java
public interface EventObserver {
    void onEvent(Event e);
}
```

```java
public abstract class EventEmitter {

    private final Map<Event, List<EventObserver>> observerLists;

    public EventEmitter() {
        observerLists = new HashMap<>();
    }

    public final void registerObserver(EventObserver obs, Event e) {
      // implementation omitted
    }

    protected void notifyObservers(Event e) {
        // implementation omitted
    }
}
```

`KingJoffrey` is listening to events from `KingsHand`.

```java

@Slf4j
public class KingJoffrey implements EventObserver {
    @Override
    public void onEvent(Event e) {
        LOGGER.info("Received event from the King's Hand: {}", e.toString());
    }
}
```

`KingsHand` is listening to events from his subordinates `LordBaelish`, `LordVarys`, and `Scout`. Whatever he hears from them, he delivers to `KingJoffrey`.

```java
public class KingsHand extends EventEmitter implements EventObserver {

    public KingsHand() {
    }

    public KingsHand(EventObserver obs, Event e) {
        super(obs, e);
    }

    @Override
    public void onEvent(Event e) {
        notifyObservers(e);
    }
}
```

For example, `LordVarys` finds a traitor every Sunday and notifies the `KingsHand`.

```java

@Slf4j
public class LordVarys extends EventEmitter implements EventObserver {
    @Override
    public void timePasses(Weekday day) {
        if (day == Weekday.SATURDAY) {
            notifyObservers(Event.TRAITOR_DETECTED);
        }
    }
}
```

The following snippet demonstrates how the objects are constructed and wired together.

```java
public static void main(String[] args) {

    var kingJoffrey = new KingJoffrey();

    var kingsHand = new KingsHand();
    kingsHand.registerObserver(kingJoffrey, Event.TRAITOR_DETECTED);
    kingsHand.registerObserver(kingJoffrey, Event.STARK_SIGHTED);
    kingsHand.registerObserver(kingJoffrey, Event.WARSHIPS_APPROACHING);
    kingsHand.registerObserver(kingJoffrey, Event.WHITE_WALKERS_SIGHTED);

    var varys = new LordVarys();
    varys.registerObserver(kingsHand, Event.TRAITOR_DETECTED);
    varys.registerObserver(kingsHand, Event.WHITE_WALKERS_SIGHTED);

    var scout = new Scout();
    scout.registerObserver(kingsHand, Event.WARSHIPS_APPROACHING);
    scout.registerObserver(varys, Event.WHITE_WALKERS_SIGHTED);

    var baelish = new LordBaelish(kingsHand, Event.STARK_SIGHTED);

    var emitters = List.of(
            kingsHand,
            baelish,
            varys,
            scout
    );

    Arrays.stream(Weekday.values())
            .<Consumer<? super EventEmitter>>map(day -> emitter -> emitter.timePasses(day))
            .forEachOrdered(emitters::forEach);
}
```

The console output after running the example.

```
21:37:38.737 [main] INFO com.iluwatar.event.aggregator.KingJoffrey -- Received event from the King's Hand: Warships approaching
21:37:38.739 [main] INFO com.iluwatar.event.aggregator.KingJoffrey -- Received event from the King's Hand: White walkers sighted
21:37:38.739 [main] INFO com.iluwatar.event.aggregator.KingJoffrey -- Received event from the King's Hand: Stark sighted
21:37:38.739 [main] INFO com.iluwatar.event.aggregator.KingJoffrey -- Received event from the King's Hand: Traitor detected
```

## Class diagram

![Event Aggregator](./etc/classes.png "Event Aggregator")

## Applicability

Use the Event Aggregator pattern when

* Use the Event Aggregator pattern in systems where multiple components generate events and multiple components need to receive those events, but direct coupling between these components leads to complex dependencies and hard-to-manage code.
* Suitable in applications where a reduction in the number of explicit references between decoupled systems is desired, such as in microservices architectures or complex user interface systems.

## Known Uses

* Enterprise application integrations where systems need a central point to handle events generated by various subsystems.
* Complex GUI applications where user actions in one part of the interface need to affect other parts without tight coupling between the components.

## Consequences

Benefits:

* Reduces Coupling: By centralizing event handling, the Event Aggregator minimizes direct interaction between components, leading to a more modular and easier-to-manage system.
* Improves Flexibility and Scalability: Adding new publishers or subscribers involves less effort since the central aggregator handles all routing.
* Simplifies Component Interface: Components need to know only about the Event Aggregator, not about other components.

Trade-offs:

* Complexity of the Aggregator: The Event Aggregator itself can become a complex and high-maintenance component if not properly designed.
* Potential Performance Bottleneck: If not scaled properly, the central event handling mechanism can become a bottleneck in the system.

## Related Patterns

* [Mediator](https://java-design-patterns.com/patterns/mediator/): Similar to Mediator in that it abstracts direct communications between components, but focused specifically on event messages.
* [Observer](https://java-design-patterns.com/patterns/observer/): The Event Aggregator pattern is often implemented using the Observer pattern, where the aggregator observes events and notifies subscribers.
* Publish-Subscribe: The Event Aggregator can be seen as a special case of the Publish-Subscribe pattern, with the aggregator acting as the broker.

## Credits

* [Design Patterns: Elements of Reusable Object-Oriented Software](https://amzn.to/44eWKXv)
* [Enterprise Integration Patterns: Designing, Building, and Deploying Messaging Solutions](https://amzn.to/440b0CZ)
* [Java Design Pattern Essentials](https://amzn.to/43XHCgM)
* [Event Aggregator (Martin Fowler)](http://martinfowler.com/eaaDev/EventAggregator.html)
