package util;

import java.io.Serializable;

/**
 * enum that takes care of all
 * actions that possibly can be
 * sent to or from the server
 * 
 * @author Magnus
 */
public enum SendableAction implements Serializable{
	LOGIN,
	LOGOUT,
	SEARCH_PLAYER,
	SIGN_UP,
	MESSAGE,
	PLAYERS_ONLINE,
	PLACE_WORD,
	START_GAME,
	QUIT_GAME,
	PASS,
	SWAP,
	GET_DICTIONARIES,
	OPPONENT_DATA,
	MAIN_THREAD,
	INVITE_GAME,
	GET_PLAYER_ONE_NAME, //TODO: Add getters for the player names that can be used in GameBoardActivity
	GET_PLAYER_TWO_NAME
}