public class Client implements Observer {
	
	public Subject message;
	public String name;
	
	public Client(Subject subj, String name){
		this.name = name;
		this.message = subj;
		this.message.setObserver(this);
		this.message.setMessage(subj.messageSet);
	}

	public void removeClient(){
		this.message.removeObsevrer(this);
	}
	
	public void update(){
		System.out.println("Hello, " + this.name + ", the message you received: " + this.message.messageSet);
		System.out.print("Hallo, " + this.name + ", deine neue Nachricht ist:  " );
		message.setDictionary();
		message.translation();
		System.out.println();
	}

}

public class Subject {
	
	protected ArrayList<Observer> observers = new ArrayList<Observer>();
	protected String messageSet;
	HashMap<String, String> hm = new HashMap<String, String>();
	
	public Subject(String message){
		this.messageSet = message;
	}
	
	public void setDictionary(){
		this.hm.put("the kids", "deine Kindern weg");
		this.hm.put("with", "mit");
		this.hm.put("left", "ging");
		this.hm.put("Your wife", "Deine Frau");
	}
	
	@SuppressWarnings("rawtypes")
	public void translation(){
		Set<Entry<String, String>> set = this.hm.entrySet();
		Iterator<Entry<String, String>> it = set.iterator();
		
		while(it.hasNext()){
			Map.Entry map = (Map.Entry)it.next();
			System.out.print(map.getValue() + " ");
		}
		System.out.print("!");
	}
	
	public void setObserver(Observer obs){
		this.observers.add(obs);
	}
	
	public void removeObsevrer(Observer obs){
		this.observers.remove(obs);
	}
	
	public void setMessage(String msg){
		this.messageSet = msg;
	}
	
	public String getMessage(){
		return this.messageSet;
	}
	
	public void notifyObservers(){
		System.out.println("Notifying clients: ");
		for(Observer obs : observers)
			obs.update();
	}

}

public class Main {

	public static void main(String[] args) {

		Subject newMessage = new Subject("Your wife left with the kids");
		
		new Client(newMessage, "John");
		new Client(newMessage, "Andy");
		
		newMessage.notifyObservers();
	}
}