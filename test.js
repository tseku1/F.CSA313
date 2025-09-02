const { Builder, By, Key, until } = require("selenium-webdriver");

async function getPersonalInfo() {
  let driver = await new Builder().forBrowser("chrome").build();

  try {
    await driver.get("https://student.must.edu.mn");

    await driver.findElement(By.id("username")).sendKeys("B222270063");
    await driver.findElement(By.id("password")).sendKeys("b9f4z2mnq");

    await driver.findElement(By.css("input.btn.btn-primary.btn-block[value='Нэвтрэх']")).click();

    let title = await driver.getTitle(); console.log("Login хийсний дараах гарчиг:", title);

    let elements = await driver.findElements(By.css(".adminControl.active"));
    if (elements.length > 0) {
      let fullName = await elements[0].getText();
      console.log("Хэрэглэгчийн нэр:", fullName);
    } else {
      console.log("Элемент олдсонгүй.");
    }


  } catch(err) {
    console.log(err);
  } finally {
    await driver.quit();
  }
}

getPersonalInfo();
