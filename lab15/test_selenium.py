from selenium import webdriver
from selenium.webdriver.common.by import By
import time
import os

if __name__ == "__main__":
    driver = webdriver.Edge()

    file_path = os.path.abspath("index.html")
    driver.get("file:///" + file_path)

    time.sleep(2)
    driver.find_element(By.XPATH, "//button[text()='Login']").click()
    time.sleep(2)

    driver.quit()
