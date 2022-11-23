# -*- coding: utf-8 -*-
import requests
import re
from bs4 import BeautifulSoup
import time
import RPi.GPIO as GPIO
#import commands
import os
import serial


LEDPin = 26
Speaker = 17
HWPin = 18

serVB = serial.Serial("/dev/ttyUSB0", 9600)


#url='http://www.weather.com.cn/weather/101130101.shtml'

url1='http://www.weather.com.cn/weather/'
url2='101020100'
url3='.shtml'


def setup():
    GPIO.setmode(GPIO.BCM)       # Numbers GPIOs by physical location
    GPIO.setup(LEDPin, GPIO.OUT)
    GPIO.setup(Speaker, GPIO.OUT)
    GPIO.setup(HWPin, GPIO.IN)
    GPIO.output(LEDPin, GPIO.LOW)
    GPIO.output(Speaker, GPIO.HIGH)
    GPIO.setwarnings(False)

'''
Use crawler to get China weather web information
-- like hangzhou: http://www.weather.com.cn/weather/101210101.shtml
'''
def getHtmlText(url,code='utf-8'):
    try:
        r = requests.get(url)
        r.raise_for_status()
        r.encoding = code
        return r.text
    except:
        return ''
def makeSoup(html):
    wstr = ''
    #print html
    if html == '':
        return "I don't know the weather in Hangzhou today"
    else:
        soup = BeautifulSoup(html,'html.parser')
        soup1 = soup.find_all('li',attrs = {'class':'on'})[1]
        str1 = re.findall(r'>(.*)</',str(soup1))
        b = ''
        try:
            slist = re.findall(r'^(.*)</span>(.*)<i>(.*)$',str1[4])
            #print slist
            for x in range(len(slist[0])):
                b += slist[0][x]
        except:
            b = str1[4]
        if '/' in b:
            b = b.replace('/','-')
        str1[4] = '!Temperature:'+b
        str1[6] = '!WindSpeed:'+str1[6]
        if '&lt;' in str1[6]:
            str1[6] = str1[6].replace('&lt;','!')
        for i in str1:
            if i != '':
                wstr = wstr +i
        if '雨' in wstr:
            wstr += "Don't forget to take the umbrella today!"
            serVB.write('w1')
            GPIO.output(LEDPin, GPIO.HIGH)
            GPIO.output(Speaker, GPIO.LOW)
            os.system('mplayer 123.mp3')
        print(wstr)
        return wstr

'''
Main Function
'''
def main():
    flag = 1	
    setup()
    while  True:
        GPIO.output(LEDPin, GPIO.LOW)
        GPIO.output(Speaker, GPIO.HIGH)
        serVB.write('w0')
        count = serVB.inWaiting()
        if count != 0:
                    recv = serVB.read(count)
                    url2 = recv
                    print(url2)
                    serVB.flushInput()
                    time.sleep(0.1)
        
        state = GPIO.input(HWPin)
        if(state==0):
                print(url1+url2+url3)
                html=getHtmlText(url1+url2+url3)
                word = makeSoup(html)

if __name__ == '__main__':
    main()
