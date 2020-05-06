package application.guest;

public enum GuestState {
	
	//WAIT -> COME -> WAIT -> COME...
	COME,//ANGRY?
	
	LEAVE,//WAIT -> LEAVE -> EXIT? DESTROY:ASK_MORE -> WAIT->COME...
	ASK_MORE,
	
	EXIT,
	ANGRY
}
