<style>

</style>

# Components

## Define

- a component is a piece of the UI (user interface) that has its own logic and appearance

## Writing markup JSX

- The markup syntax you’ve seen above is called JSX.
It is optional, but most React projects use JSX for its convenience.
All of the tools we recommend for local development support JSX out of the box.
- JSX is stricter (chặt chẽ) than HTML.

## Example Coding

### Conditional rendering

- If you prefer more compact code, you can use the conditional ? operator. Unlike if, it works inside JSX:

``` javascript
<div>{isLoggedIn ? (<AdminPanel />) : (<LoginForm />)}</div>
```

### Rendering lists

-You will rely on JavaScript features like for loop and the array map() function to render lists of components.

``` javascript
export default function ShoppingList() {
  const listItems = products.map(product =>
    <li
      key={product.id}
      style={{
        color: product.isFruit ? 'magenta' : 'darkgreen'
      }}
    >
      {product.title}
    </li>
  );
  return (
    <ul>{listItems}</ul>
  );
}
```

## Question

<mark>
1.What is the difference between components, elements, and instances?
</mark>
