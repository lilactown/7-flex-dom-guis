# 7 flex/dom GUIs

An example project using [flex](https://github.com/lilactown/flex) and
[dom](https://github.com/lilactown/dom) together.

Implements tasks 1-5 of the [7 GUIs](https://eugenkiss.github.io/7guis/tasks/).

## Important bits

[town.lilac.flex.dom/scope](./src/town/lilac/flex/dom.cljc) is the macro that
bridges the flex effect system to DOM patching. It expects that the body will
execute a number of `town.lilac.dom` expressions. Any signals or sources
dereferenced in the body will trigger a patch of the last element returned.

[town.lilac.guis.main/start!](./src/town/lilac/guis/main.cljs) demos how to
dispose of and recreate the effect graph on hot reload.

## Developing

Install JS dependencies (shadow-cljs, incremental-dom) via `npm install` the
first time.

Start the `:app` build via shadow-cljs (e.g. `npx shadow-cljs watch app`), open
your browser to http://localhost:3456 and you're off!
