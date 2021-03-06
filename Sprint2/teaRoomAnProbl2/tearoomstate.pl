%==============================================
% tearoomstate.pl
%==============================================
%==============================================
% Positions

pos( home, 0, 0 ).
pos( bar, 6, 0 ).
pos( entrance, 0, 4).
pos( exit, 6, 4 ).
pos( table1, 2, 2 ).
pos( table2, 4, 2 ).

%==============================================
%==============================================
 
% Tables


% tableOccupied(ID)
% tableDirty
% tableClearing
% tableSanitizing
% tableCleaning
% tableCleaned
%==========
% ultimo parametro rappresenta il tempo di pulizia eseguito 
% della fase al parametro rappresentante lo stato (sopra elencato)


stateTable(1,tableCleaned,0).
stateTable(2,tableCleaned,0).


setStateTable(TAVOLO, STATO, TEMPO) :-
	retract(  stateTable(TAVOLO, _, _)),
	!,
	assert( stateTable(TAVOLO, STATO, TEMPO)).

occupyTable(TAVOLO, ID) :-
	retract(  stateTable(TAVOLO, tableCleaned, _)),
	!,
	assert( stateTable(TAVOLO, tableOccupied(ID),0)).

%==============================================
%==============================================

% WAITER

waiter( home ).

setWaiter(STATO) :-
	retract( waiter(_) ),
	!,
	assert(waiter(STATO)).

%==============================================
%==============================================

% Barman


% waitOrder
% prepareOrder(ID, ORDER)


barman( waitOrder ).

setBarman(STATO ) :-
	retract(barman(_)),
	!,
	assert(barman(STATO)).
	
addOrderReady(ID) :-
	assert(order(ID)).

removeOrderReady(ID) :-
	retract(order(ID)),
	!.

%==============================================
%==============================================

% Counter

clientiAccettati(0).
clientiRifiutati(0).


inc(X,Y):- Y is X+1.
incAccettati:- clientiAccettati(N),!,inc(N,Y),retract(clientiAccettati(N)),assert(clientiAccettati(Y)).
incRifiutati:- clientiRifiutati(N),!,inc(N,Y),retract(clientiRifiutati(N)),assert(clientiRifiutati(Y)).


setClientiAccettati(N ) :-
	retract(clientiAccettati(_)),
	!,
	assert(clientiAccettati(N)).


setClientiRifiutati(N ) :-
	retract(clientiRifiutati(_)),
	!,
	assert(clientiRifiutati(N)).

%==============================================
%==============================================

% state

statetearoom( waiter(SW), barman(SB),  stateTable(1, ST1, T1), stateTable(2, ST2, T2), clientiAccettati(NA), clientiRifiutati(NR) ) :-
				waiter(SW), barman(SB),  stateTable(1, ST1, T1), stateTable(2, ST2, T2), clientiAccettati(NA), clientiRifiutati(NR).







	