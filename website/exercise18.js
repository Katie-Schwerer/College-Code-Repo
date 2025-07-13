class BananaadeStand {
  bananas = 0;
  gallonsOfWater = 0;
  cupsOfSugar = 0;
  emptyGlasses = 0;
  glassesOfBananaade = 0;
  price = 0;
  income = 0;

  constructor(bananas, gallonsOfWater, cupsOfSugar, emptyGlasses, price) {
    this.bananas = bananas
    this.gallonsOfWater = gallonsOfWater
    this.cupsOfSugar = cupsOfSugar
    this.emptyGlasses = emptyGlasses
    this.price = price
  }

  makeBananaade() {
    if(this.bananas >= 6 && this.gallonsOfWater >= 1 && this.cupsOfSugar >= 1 && this.emptyGlasses >= 8) {
      this.bananas -= 6;
      this.gallonsOfWater -= 1;
      this.cupsOfSugar -= 1;
      this.emptyGlasses -= 8;
      this.glassesOfBananaade += 8;
      return this.glassesOfBananaade;
    } else {
      return this.glassesOfBananaade;
    }
  }

  sellBananaade() {
    if (this.glassesOfBananaade > 0) {
      this.glassesOfBananaade -= 1
      this.income += this.price;
      return 1;
    } else {
      if (makeBananaade() > 0) {
        this.glassesOfBananaade -= 1;
        this.income += this.price;
        return 1;
      } else {
        return 0;
      }
    }
  }

  sellMoreBananaade(requestedGlasses) {
    if(this.glassesOfBananaade > 0) 
    {
      if(requestedGlasses <= 8 && this.glassesOfBananaade >= requestedGlasses) 
      {
        this.glassesOfBananaade -= requestedGlasses;
        this.income += this.price * requestedGlasses;
        return requestedGlasses;
      }
      else 
      {
        if (this.glassesOfBananaade >= 8 && requestedGlasses > 8)
        {
          this.glassesOfBananaade -= 8;
          this.income += this.price * 8
          return 8;
        }
        else if (requestedGlasses > this.glassesOfBananaade)
        {
          if (this.makeBananaade() >= 8) {
            this.glassesOfBananaade -= requestedGlasses;
            this.income += this.price * requestedGlasses;
            return requestedGlasses;
          }
          else 
          {
            this.income += this.price * this.glassesOfBananaade;
            let sold = this.glassesOfBananaade;
            this.glassesOfBananaade = 0;
            return sold;
          }
        }
      }
    }
    else if(makeBananaade() > 0) 
    {
      this.glassesOfBananaade -= requestedGlasses;
      this.income += this.price * requestedGlasses;
      return requestedGlasses;
    }
    else 
    {
      return 0;
    }
    return 0;
  }

  showIngredients(element) {
    let myTableSection = document.getElementById('ingredients');
    let article2 = myTableSection.getElementsByTagName(element);

    let ingred = "<p>Ingredients</p>";
    ingred += "<table>";

    ingred += "<tr>";
    ingred += "<td>Bananas</td>";
    ingred += `<td>${this.bananas}</td>`;
    ingred += "</tr>";

    ingred += "<tr>";
    ingred += "<td>Water</td>";
    ingred += `<td>${this.gallonsOfWater}</td>`;
    ingred += "</tr>";

    ingred += "<tr>";
    ingred += "<td>Sugar</td>";
    ingred += `<td>${this.cupsOfSugar}</td>`;
    ingred += "</tr>";
    
    ingred += "<tr>";
    ingred += "<td>Empty Glasses</td>";
    ingred += `<td>${this.emptyGlasses}</td>`;
    ingred += "</tr>";

    ingred += "</table>";

    article2.textContent = ingred;
    myTableSection.innerHTML = article2.textContent;
    
  } 

  showAdmin(element) {
    let admin = document.getElementById('admin');
    let article = admin.getElementsByTagName(element);

    let list = "<h1>Admin</h1>";

    list += "<ul>";
    list += `<li>Price per Glass: $${this.price.toFixed(2)}</li>`;
    list += `<li>Glasses of Bananaade: ${this.glassesOfBananaade}</li>`;
    list += `<li>Income: $${this.income.toFixed(2)}</li>`;
    list += "</ul>";

    article.textContent = list;
    admin.innerHTML = article.textContent;
    
  }
}


function test1() {
  let ls = new BananaadeStand(15,3,4,20,1.5);
  ls.makeBananaade();
  ls.sellBananaade();
  ls.sellMoreBananaade(8);
  ls.showIngredients('article');
  ls.showAdmin('article');
}

test1();