/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import React, { Component } from 'react';
import {
  AppRegistry,
  StyleSheet,
  Text,
  Image,
  View,
  TouchableOpacity,
  DrawerLayoutAndroid
} from 'react-native';

export default class Weather extends Component {
  render() {
    var navigationView = (
  <View style={styles.container}>
    <View style={styles.nav_top_view}>
        <Image 
           style={{width: 50, height: 50}}
           source={require('./img/head.jpg')}
        />
        <Text
          style={{marginTop:10,fontSize:16,color:'white'}}>非著名程序员</Text>
    </View>
   <TouchableOpacity
      onPress={this.close}
    >
    <View style={styles.nav_item_view}>
      <Image 
         style={{width: 20, height: 20}}
         source={require('./img/nav_icon_home.png')}
      />
      <Text
        style={{fontSize:14,color:'grey',marginLeft:10}}>首页</Text>
    </View>
    </TouchableOpacity>
    <View style={styles.nav_item_view}>
    <Image 
       style={{width: 20, height: 20}}
       source={require('./img/nav_icon_gift.png')}
    />
    <Text
      style={{fontSize:14,color:'grey',marginLeft:10}}>礼物</Text>
    </View>
    <View style={styles.nav_item_view}>
    <Image 
       style={{width: 20, height: 20}}
       source={require('./img/nav_icon_settings.png')}
    />
    <Text
      style={{fontSize:14,color:'grey',marginLeft:10}}>设置</Text>
    </View>
  </View>
  );
    return (
      <View style={styles.container}>
        <Text style={styles.welcome}>
          Welcome to React Native!
        </Text>
        <Text style={styles.instructions}>
          To get started, edit index.android.js
        </Text>
        <Text style={styles.instructions}>
          Double tap R on your keyboard to reload,{'\n'}
          Shake or press menu button for dev menu
        </Text>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
  },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
  instructions: {
    textAlign: 'center',
    color: '#333333',
    marginBottom: 5,
  },
});

AppRegistry.registerComponent('Weather', () => Weather);
