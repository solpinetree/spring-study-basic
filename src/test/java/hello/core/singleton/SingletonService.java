package hello.core.singleton;

// 싱글톤 패턴을 구현하는 방법은 여러가지가 있지만, 여기서는 객체를 미리 생성해두는 가장 단순하고 안전한 방법을 선택
public class SingletonService {

    //1. static 영역에 객체 instance를 미리 하나 생성해서 올려둔다.
    private static final SingletonService instance = new SingletonService();

    //2. SingletonService 객체 인스턴스가 필요하면 오직 getInstanced() 메서드를 통해서만 조회 가능.
    // 항상 같은 인스턴스를 반환
    public static SingletonService getInstance() {
        return instance;
    }

    //3. 딱 1개의 객체 인스턴스만 존재해야 하므로, 생성자를 private으로 막는다. (new 키워드 사용 금지시킴)
    private SingletonService() {
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }

}

