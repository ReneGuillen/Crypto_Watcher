(this.webpackJsonpwebsite=this.webpackJsonpwebsite||[]).push([[0],{169:function(e,c,t){},188:function(e,c,t){},395:function(e,c,t){},396:function(e,c,t){"use strict";t.r(c);var a=t(11),n=t.n(a),s=t(164),r=t.n(s),o=t(113),i=t(47),l=(t(169),t(46)),j=t.n(l),m=(t(188),t(2)),u=function(e){var c=e.name,t=e.price,a=e.symbol,n=e.marketcap,s=e.volume,r=e.image,o=e.priceChange,i=e.icon;return Object(m.jsx)("div",{className:"coin-container",children:Object(m.jsxs)("div",{className:"coin-row",children:[Object(m.jsxs)("div",{className:"coin",children:[Object(m.jsx)("img",{src:r,alt:"crypto"}),Object(m.jsx)("h1",{children:c}),Object(m.jsx)("p",{className:"coin-symbol",children:a})]}),Object(m.jsxs)("div",{className:"coin-data",children:[Object(m.jsxs)("p",{className:"coin-price",children:["$",t]}),Object(m.jsxs)("p",{className:"coin-volume",children:["$",s.toLocaleString()]}),o<0?Object(m.jsxs)("p",{className:"coin-percent red",children:[o.toFixed(2),"%"]}):Object(m.jsxs)("p",{className:"coin-percent green",children:[o.toFixed(2),"%"]}),Object(m.jsxs)("p",{className:"coin-marketcap",children:["Mkt Cap: $",n.toLocaleString()]}),i]})]})})},p=(t(190),t(395),function(e){var c=e.name,t=e.price,a=e.symbol,n=e.marketcap,s=e.volume,r=e.image,o=e.priceChange,i=e.icon;return Object(m.jsx)("div",{className:"coin-container2",children:Object(m.jsxs)("div",{className:"coin-row2",children:[Object(m.jsxs)("div",{className:"coin2",children:[Object(m.jsx)("img",{src:r,alt:"crypto"}),Object(m.jsx)("h1",{children:c}),Object(m.jsx)("p",{className:"coin-symbol2",children:a})]}),Object(m.jsxs)("div",{className:"coin-data2",children:[Object(m.jsxs)("p",{className:"coin-price2",children:["$",t]}),Object(m.jsxs)("p",{className:"coin-volume2",children:["$",s.toLocaleString()]}),o<0?Object(m.jsxs)("p",{className:"coin-percent red2",children:[o.toFixed(2),"%"]}):Object(m.jsxs)("p",{className:"coin-percent green2",children:[o.toFixed(2),"%"]}),Object(m.jsxs)("p",{className:"coin-marketcap2",children:["Mkt Cap: $",n.toLocaleString()]}),i]})]})})}),h=t(112),d=t(111);var b=function(){var e=Object(a.useState)(""),c=Object(i.a)(e,2),t=(c[0],c[1]);Object(a.useEffect)((function(){j.a.get("https://cryptocurrency-watcher.herokuapp.com/addUser").then((function(e){t(e.data)})).catch((function(e){return console.log(e)}))}));var n=Object(a.useState)(""),s=Object(i.a)(n,2),r=s[0],l=s[1],b=Object(a.useState)([]),O=Object(i.a)(b,2),f=O[0],x=O[1],g=Object(a.useState)([]),v=Object(i.a)(g,2),N=v[0],k=v[1],y=Object(a.useState)([]),w=Object(i.a)(y,2),S=w[0],_=w[1],C=Object(a.useState)(""),L=Object(i.a)(C,2),$=L[0],E=L[1];Object(a.useEffect)((function(){j.a.get("https://cryptocurrency-watcher.herokuapp.com/getUser").then((function(e){E(e.data)})).catch((function(e){return console.log(e)}))})),Object(a.useEffect)((function(){j.a.get("https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&order=market_cap_desc&per_page=50&page=1&sparkline=false").then((function(e){k(e.data)})).catch((function(e){return console.log(e)}))}),[]),Object(a.useEffect)((function(){j.a.get("https://cryptocurrency-watcher.herokuapp.com/showStocks").then((function(e){x(e.data)})).catch((function(e){return console.log(e)}))}),[]);for(var F=new Set,M=0;M<f.length;M++)""!=f[M]&&F.add(f[M]);for(var z=function(e){var c=N[e];if(F.has(c.name)){var t=N.filter((function(e){return e.name!==c.name}));k(t),_(S.concat(c))}},A=0;A<N.length;A++)z(A);var J=N.filter((function(e){return e.name.toLowerCase().includes(r.toLowerCase())})),U=S.filter((function(e){return e.name.toLowerCase().includes(r.toLowerCase())}));return Object(m.jsxs)("div",{className:"app",children:[Object(m.jsxs)("div",{className:"navbar",children:[Object(m.jsxs)("h1",{className:"welcome",children:[Object(m.jsx)(d.b,{size:"30px"}),"  Hello ",$]}),Object(m.jsx)("a",{href:"https://cryptocurrency-watcher.herokuapp.com/logout",className:"log-button",children:Object(m.jsx)("h1",{className:"txt-logo",children:"Logout"})})]}),Object(m.jsxs)("div",{className:"coin-app",children:[Object(m.jsxs)("div",{className:"coin-search",children:[Object(m.jsx)("h1",{className:"coin-text",children:" Crypto Watcher"}),Object(m.jsx)("form",{children:Object(m.jsx)("input",{type:"text",placeholder:"Search",className:"coin-input",onChange:function(e){l(e.target.value)}})})]}),Object(m.jsx)(d.a,{className:"star-icon",size:"35px",color:"yellow"}),Object(m.jsx)("div",{className:"favorite-list",children:S.length<=0?Object(m.jsx)("h1",{className:"fav-txt",children:"Add coins to your fav list! : )"}):U.map((function(e){return Object(m.jsx)(p,{name:e.name,symbol:e.symbol,image:e.image,marketcap:e.market_cap,price:e.current_price,priceChange:e.price_change_percentage_24h,volume:e.total_volume,icon:Object(m.jsx)(h.b,{onClick:function(){return function(e){j.a.get("https://cryptocurrency-watcher.herokuapp.com/deleteStock?stock="+e.name);var c=f.filter((function(c){return c!==e.name}));x(c);var t=S.filter((function(c){return c.name!==e.name}));_(t);var a=[e].concat(Object(o.a)(N));k(a)}(e)},className:"react-icon2"})},e.id)}))}),Object(m.jsx)("h1",{className:"fav-tag",children:" All Coins "}),Object(m.jsx)("div",{className:"all-coins",children:J.map((function(e){return Object(m.jsx)(u,{name:e.name,symbol:e.symbol,image:e.image,marketcap:e.market_cap,price:e.current_price,priceChange:e.price_change_percentage_24h,volume:e.total_volume,icon:Object(m.jsx)(h.a,{onClick:function(){return function(e){j.a.get("https://cryptocurrency-watcher.herokuapp.com/addStock?stock="+e.name);var c=N.filter((function(c){return c.name!==e.name}));k(c);var t=[e].concat(Object(o.a)(S));_(t)}(e)},className:"react-icon"})},e.id)}))})]})]})};r.a.render(Object(m.jsx)(n.a.StrictMode,{children:Object(m.jsx)(b,{})}),document.getElementById("root"))}},[[396,1,2]]]);
//# sourceMappingURL=main.aa6f9973.chunk.js.map