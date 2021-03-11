import SwiftUI
import shared

func greet() -> String {
    return EngineSDK().latestSongInteractor.getTest()
}

struct ContentView: View {
    @State var tapCount = 0
    @State var loadingState = "not loaded"
    var body: some View {
        VStack {
            Text("\(loadingState)")
            TestButton()
            Button(action: {
                self.loadingState = "loading..."
                EngineSDK().latestSongInteractor.getLatestSongsListOrException(completionHandler: { result, error in
                    if let result = result {
                        switch (result) {
                        case let success as LatestSongResult.Result:
                            let song = success.latestSongList[0]
                            self.loadingState = "\(song.artist) - \(song.title)"
                        case let error as LatestSongResult.Exception:
                            self.loadingState = error.exception
                        default:
                            self.loadingState = "default"
                        }
                    }
                })
            },
                    label: {
                        Text("Load")
                    })
            Button(action: {
                self.loadingState = EngineSDK().settingsInteractor.getTestSettings()
            },
                    label: {
                        Text("Get setting")
                    })
            Button(action: {
                EngineSDK().settingsInteractor.setTestSettings(value: "new value3")
            },
                    label: {
                        Text("Set setting")
                    })
        }
    }
}

struct TestButton: View {
    var body: some View {
        Button(action: {
            EngineSDK().latestSongInteractor.getTest()
        },
                label: {
                    Text("Test")
                })
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
