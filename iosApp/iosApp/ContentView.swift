import SwiftUI
import shared

func greet() -> String {
    return EngineSDK().pageGenerator.getGreeting()
}

struct ContentView: View {
    var body: some View {
        Text(greet())
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
