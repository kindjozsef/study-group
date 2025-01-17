# What is yaml?

- it is used to represent data (for example configuration data in case of kubernetes)

# Example: Key Value Pair
Note: you must put a ` ` (space) between the KEY and VALUE
```yaml
Fruit: Apple
Vegetable: Carrot
# This is a comment
```

# Example: Storing array/list
`-` indicates that it is an element of an array\
Do not forget the space ` `  between the `value` and `-`
```yaml
Fruits:
    - Orange
    - Apple
    - Banana
Vegetables:
    - Carrot
    - Tomato
```

# Example: Dictionary/Map
It is a set of properties grouped together under an item
```yaml
Banana:
    Calories: 105
    Fat: 0.4 g
    Carbs: 27 g
Grapes:
    Calories: 62
    Fat: 0.3 g
    Carbs: 16 g
```